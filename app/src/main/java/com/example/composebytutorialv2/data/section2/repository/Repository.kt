package com.example.composebytutorialv2.data.section2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.composebytutorialv2.data.section2.database.DbMapper
import com.example.composebytutorialv2.data.section2.database.model.ColorDb
import com.example.composebytutorialv2.data.section2.database.model.NoteDb
import com.example.composebytutorialv2.data.section2.model.Color
import com.example.composebytutorialv2.data.section2.model.Note
import com.example.composebytutorialv2.ui.section2.data.database.dao.ColorDao
import com.example.composebytutorialv2.ui.section2.data.database.dao.NoteDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface Repository {
    // notes
    fun getAllNotesNotInTrash(): LiveData<List<Note>>
    fun getAllNotesInTrash(): LiveData<List<Note>>
    fun getNote(id: Long): LiveData<Note>
    fun insertNote(note: Note)
    fun deleteNote(id: Long)
    fun deleteNotes(noteIds: List<Long>)
    fun moveNoteToTrash(noteId: Long)
    fun restoreNotesFromTrash(noteIds: List<Long>)
    // colors
    fun getAllColors(): LiveData<List<Color>>
    fun getAllColorsSync(): List<Color>
    fun getColor(id: Long): LiveData<Color>
    fun getColorSync(id: Long): Color
}

class RepositoryImpl(
    private val noteDao: NoteDao,
    private val colorDao: ColorDao,
    private val dbMapper: DbMapper
) : Repository {

    private val notesNotInTrashLiveData: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    private val notesInTrashLiveData: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    init {
        initDatabase(this::updateNotesLiveData)
    }

    //Populates database with colors if it is empty.
    private fun initDatabase(postInitAction: () -> Unit) {
        GlobalScope.launch {
            // Prepopulate colors
            val colors = ColorDb.DEFAULT_COLORS.toTypedArray()
            val dbColors = colorDao.getAllSync()
            if (dbColors.isNullOrEmpty()) {
                colorDao.insertAll(*colors)
            }
            // Prepopulate notes
            val notes = NoteDb.DEFAULT_NOTES.toTypedArray()
            val dbNotes = noteDao.getAllSync()
            if (dbNotes.isNullOrEmpty()) {
                noteDao.insertAll(*notes)
            }

            postInitAction.invoke()
        }
    }

    override fun getAllNotesNotInTrash(): LiveData<List<Note>> = notesNotInTrashLiveData

    override fun getAllNotesInTrash(): LiveData<List<Note>> = notesInTrashLiveData

    private fun getAllNotesDependingOnTrashStateSync(inTrash: Boolean): List<Note> {
        val colorDbModels: Map<Long, ColorDb> = colorDao.getAllSync().map { it.id to it }.toMap()
        val dbNotesNotInTrashes: List<NoteDb> =
            noteDao.getAllSync().filter { it.isInTrash == inTrash }
        return dbMapper.mapNotes(dbNotesNotInTrashes, colorDbModels)
    }

    override fun getNote(id: Long): LiveData<Note> =
        Transformations.map(noteDao.findById(id)) {
            val colorDbModel = colorDao.findByIdSync(it.colorId)
            dbMapper.mapNote(it, colorDbModel)
        }

    override fun insertNote(note: Note) {
        noteDao.insert(dbMapper.mapDbNote(note))
        updateNotesLiveData()
    }

    override fun deleteNote(id: Long) {
        noteDao.delete(id)
        updateNotesLiveData()
    }

    override fun deleteNotes(noteIds: List<Long>) {
        noteDao.delete(noteIds)
        updateNotesLiveData()
    }

    override fun moveNoteToTrash(noteId: Long) {
        val dbNote = noteDao.findByIdSync(noteId)
        val newDbNote = dbNote.copy(isInTrash = true)
        noteDao.insert(newDbNote)
        updateNotesLiveData()
    }

    override fun restoreNotesFromTrash(noteIds: List<Long>) {
        val dbNotesInTrash = noteDao.getNotesByIdsSync(noteIds)
        dbNotesInTrash.forEach {
            val newDbNote = it.copy(isInTrash = false)
            noteDao.insert(newDbNote)
        }
        updateNotesLiveData()
    }

    override fun getAllColors(): LiveData<List<Color>> =
        Transformations.map(colorDao.getAll()) { dbMapper.mapColors(it) }

    override fun getAllColorsSync(): List<Color> = dbMapper.mapColors(colorDao.getAllSync())

    override fun getColor(id: Long): LiveData<Color> =
        Transformations.map(colorDao.findById(id)) { dbMapper.mapColor(it) }

    override fun getColorSync(id: Long): Color = dbMapper.mapColor(colorDao.findByIdSync(id))

    private fun updateNotesLiveData() {
        notesNotInTrashLiveData.postValue(getAllNotesDependingOnTrashStateSync(false))
        val newNotesInTrashLiveData = getAllNotesDependingOnTrashStateSync(true)
        notesInTrashLiveData.postValue(newNotesInTrashLiveData)
    }
}