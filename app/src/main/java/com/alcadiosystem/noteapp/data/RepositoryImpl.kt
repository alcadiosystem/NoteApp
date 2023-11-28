package com.alcadiosystem.noteapp.data

import com.alcadiosystem.noteapp.domain.entity.Note
import com.alcadiosystem.noteapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao:Repository
) :Repository {

    override suspend fun insertNote(note: Note) { dao.insertNote(note) }

    override suspend fun updateNote(note: Note) { dao.updateNote(note) }

    override suspend fun deleteNote(note: Note) { dao.deleteNote(note) }

    override fun getNotes(): Flow<List<Note>> = dao.getNotes()

    override suspend fun getNoteById(id: Int): Note = dao.getNoteById(id)
}