package com.alcadiosystem.noteapp.domain.usecase

import com.alcadiosystem.noteapp.data.RepositoryImpl
import com.alcadiosystem.noteapp.domain.entity.Note
import javax.inject.Inject

data class NoteUseCase(
    val getNote: GetNotes,
    val insertNote: InsertNote,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote,
    val getNoteById: GetNoteById
)

class GetNotes @Inject constructor( private val repository:RepositoryImpl){
    operator fun invoke() = repository.getNotes()
}

class InsertNote @Inject constructor(private val repository: RepositoryImpl){
    suspend operator fun invoke(note: Note) = repository.insertNote(note)
}

class DeleteNote @Inject constructor(private val repository: RepositoryImpl){
    suspend operator fun invoke(note:Note) = repository.deleteNote(note)
}

class UpdateNote @Inject constructor(private val repository: RepositoryImpl){
    suspend operator fun invoke(note:Note) = repository.updateNote(note)
}

class GetNoteById @Inject constructor(private val repository: RepositoryImpl){
    suspend operator fun invoke(id:Int) = repository.getNoteById(id)
}