package com.alcadiosystem.noteapp.di

import android.content.Context
import androidx.room.Room
import com.alcadiosystem.noteapp.data.NoteDatabase
import com.alcadiosystem.noteapp.data.RepositoryImpl
import com.alcadiosystem.noteapp.domain.usecase.DeleteNote
import com.alcadiosystem.noteapp.domain.usecase.GetNoteById
import com.alcadiosystem.noteapp.domain.usecase.GetNotes
import com.alcadiosystem.noteapp.domain.usecase.InsertNote
import com.alcadiosystem.noteapp.domain.usecase.NoteUseCase
import com.alcadiosystem.noteapp.domain.usecase.UpdateNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context):NoteDatabase{
        return Room.databaseBuilder(
            context=context,
            klass = NoteDatabase::class.java,
            name = "note.db"
        ).build()
    }

    @Provides
    fun provideDao(dao:NoteDatabase)=dao.noteDao()

    @Provides
    fun provideNoteUseCase(repository: RepositoryImpl) = NoteUseCase(
        getNote = GetNotes(repository),
        getNoteById = GetNoteById(repository),
        insertNote = InsertNote(repository),
        deleteNote = DeleteNote(repository),
        updateNote = UpdateNote(repository)
    )

}