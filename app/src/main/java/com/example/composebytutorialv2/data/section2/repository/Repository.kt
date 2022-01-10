package com.example.composebytutorialv2.data.section2.repository

import com.example.composebytutorialv2.data.section2.database.DbMapper
import com.example.composebytutorialv2.data.section2.database.dao.ColorDao
import com.example.composebytutorialv2.data.section2.database.dao.NoteDao
import com.example.composebytutorialv2.data.section2.database.model.ColorDb
import com.example.composebytutorialv2.data.section2.database.model.NoteDb
import com.example.composebytutorialv2.data.section2.model.ColorModel
import com.example.composebytutorialv2.data.section2.model.NoteModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface Repository {
    // notes
    fun getAllNotesFlow(): Flow<List<NoteModel>>
    fun getAllNotesInTrashFlow(): Flow<List<NoteModel>>
    fun getNote(id: Long): Flow<NoteModel>
    fun insertOrReplaceNote(noteModel: NoteModel)
    fun deleteNote(id: Long)
    fun deleteNotes(noteIds: List<Long>)
    fun moveNoteToTrash(noteId: Long)
    fun restoreNotesFromTrash(noteIds: List<Long>)
    // colors
    fun getAllColors(): Flow<List<ColorModel>>
    fun getAllColorsSync(): List<ColorModel>
    fun getColor(id: Long): Flow<ColorModel>
    fun getColorSync(id: Long): ColorModel
}

class RepositoryImpl(
    private val noteDao: NoteDao,
    private val colorDao: ColorDao,
    private val dbMapper: DbMapper
) : Repository {

    private val allNotesFlow: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    private val notesInTrashFlow: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    init {
        initDatabase(this::updateNotesFlow)
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

    override fun getAllNotesFlow(): Flow<List<NoteModel>> = allNotesFlow

    override fun getAllNotesInTrashFlow(): Flow<List<NoteModel>> = notesInTrashFlow

    private fun getAllNotesDependingOnTrashStateSync(inTrash: Boolean): List<NoteModel> {
        val colorDb: Map<Long, ColorDb> = colorDao.getAllSync().map { it.id to it }.toMap()
        val notesDbByTrashState: List<NoteDb> = noteDao.getAllSync().filter {
            it.isInTrash == inTrash
        }
        return dbMapper.mapNotes(notesDbByTrashState, colorDb)
    }

    override fun getNote(id: Long): Flow<NoteModel> =
        noteDao.findById(id).map {
            val colorDbModel = colorDao.findByIdSync(it.colorId)
            dbMapper.mapNote(it, colorDbModel)
        }

    override fun insertOrReplaceNote(noteModel: NoteModel) {
        noteDao.insertOrReplace(dbMapper.mapDbNote(noteModel))
        updateNotesFlow()
    }

    override fun deleteNote(id: Long) {
        noteDao.delete(id)
        updateNotesFlow()
    }

    override fun deleteNotes(noteIds: List<Long>) {
        noteDao.delete(noteIds)
        updateNotesFlow()
    }

    override fun moveNoteToTrash(noteId: Long) {
        val dbNote = noteDao.findByIdSync(noteId)
        val newDbNote = dbNote.copy(isInTrash = true)
        noteDao.insertOrReplace(newDbNote)
        updateNotesFlow()
    }

    override fun restoreNotesFromTrash(noteIds: List<Long>) {
        val dbNotesInTrash = noteDao.getNotesByIdsSync(noteIds)
        dbNotesInTrash.forEach {
            val newDbNote = it.copy(isInTrash = false)
            noteDao.insertOrReplace(newDbNote)
        }
        updateNotesFlow()
    }

    override fun getAllColors(): Flow<List<ColorModel>> =
        colorDao.getAll().map { dbMapper.mapColors(it) }

    override fun getAllColorsSync(): List<ColorModel> = dbMapper.mapColors(colorDao.getAllSync())

    override fun getColor(id: Long): Flow<ColorModel> =
        colorDao.findById(id).map { dbMapper.mapColor(it) }

    override fun getColorSync(id: Long): ColorModel = dbMapper.mapColor(colorDao.findByIdSync(id))

    private fun updateNotesFlow() {
        allNotesFlow.value = getAllNotesDependingOnTrashStateSync(false)
        notesInTrashFlow.value = getAllNotesDependingOnTrashStateSync(true)
    }
}