package com.alcadiosystem.noteapp.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alcadiosystem.noteapp.domain.entity.Note
import com.alcadiosystem.noteapp.domain.repository.Repository

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao():Repository

}