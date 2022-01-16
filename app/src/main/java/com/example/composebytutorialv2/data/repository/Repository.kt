package com.example.composebytutorialv2.data.repository

import com.example.composebytutorialv2.data.database.DbMapper
import com.example.composebytutorialv2.data.database.dao.section2.ColorDao
import com.example.composebytutorialv2.data.database.dao.section2.NoteDao
import com.example.composebytutorialv2.data.database.dao.section3.PostDao
import com.example.composebytutorialv2.data.database.model.section2.ColorDb
import com.example.composebytutorialv2.data.database.model.section2.NoteDb
import com.example.composebytutorialv2.data.database.model.section3.PostDbModel
import com.example.composebytutorialv2.data.model.section2.ColorModel
import com.example.composebytutorialv2.data.model.section2.NoteModel
import com.example.composebytutorialv2.data.model.section3.PostModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
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
    //post
    fun getAllPosts(): Flow<List<PostModel>>
    fun getAllOwnedPosts(): Flow<List<PostModel>>
    fun insertPost(post: PostModel)
    fun deleteAllPost()
    fun getAllSubreddits(searchedText: String): List<String>
}

class RepositoryImpl(
    private val dbMapper: DbMapper,
    private val noteDao: NoteDao,
    private val colorDao: ColorDao,
    private val postDao: PostDao
) : Repository {

    private val allNotesFlow: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    private val notesInTrashFlow: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    private val allPostsFlow: MutableStateFlow<List<PostModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    private val ownedPostsFlow: MutableStateFlow<List<PostModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    private var searchedText = ""

    init {
        initDatabase {
            updateNotesFlow()
            updatePostFlow()
        }
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
            // Prepopulate posts
            val posts = PostDbModel.DEFAULT_POSTS.toTypedArray()
            val dbPosts = postDao.getAllPosts()
            if (dbPosts.isNullOrEmpty()) {
                postDao.insertAll(*posts)
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

    override fun getAllPosts(): Flow<List<PostModel>> = allPostsFlow

    override fun getAllOwnedPosts(): Flow<List<PostModel>> = ownedPostsFlow

    private fun getAllPostsFromDatabase(): List<PostModel> =
        postDao.getAllPosts().map(dbMapper::mapPost)

    private fun getAllOwnedPostsFromDatabase(): List<PostModel> =
        postDao.getAllOwnedPosts("raywenderlich.com").map(dbMapper::mapPost)

    override fun insertPost(post: PostModel) {
        postDao.insert(dbMapper.mapDbPost(post))
        updatePostFlow()
    }

    override fun deleteAllPost() {
        postDao.deleteAll()
        updatePostFlow()
    }

    private fun updatePostFlow() {
        allPostsFlow.update { getAllPostsFromDatabase() }
        ownedPostsFlow.update { getAllOwnedPostsFromDatabase() }
    }

    override fun getAllSubreddits(searchedText: String): List<String> {
        this.searchedText = searchedText

        if (searchedText.isNotEmpty()) {
            return postDao.getAllSubreddits().filter { it.contains(searchedText) }
        }

        return postDao.getAllSubreddits()
    }
}