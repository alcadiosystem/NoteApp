package com.alcadiosystem.noteapp.presentation.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alcadiosystem.noteapp.domain.entity.Note
import com.alcadiosystem.noteapp.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CRUDViewModel @Inject constructor(
    private val useCase: NoteUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var titleBar by mutableStateOf("Add New Note")
    var titleButton by mutableStateOf("Add Note")
    var isUpdate by mutableStateOf(false)
    val notes = useCase.getNote()

    var id = savedStateHandle.get<Int>(key = "id")

    init {
        viewModelScope.launch {
            if(id!=null) {
                isUpdate = true
                titleBar = "Update note"
                titleButton = "Update Note"
                val nota = useCase.getNoteById(id!!)
                description = nota.description
                title = nota.title

            }
        }
    }

    fun addNote() =
        viewModelScope.launch {
            useCase.insertNote(Note(title=title, description = description))
        }

    fun updateNote(){
        viewModelScope.launch {
            useCase.updateNote(Note(
                id = id!!,
                title = title,
                description = description
            ))
        }
    }

    fun deleteNote(note:Note) = viewModelScope.launch {
        useCase.deleteNote(note)
    }
}