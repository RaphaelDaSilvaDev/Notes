package com.bits.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bits.notes.dao.NoteDao
import com.bits.notes.entities.Note

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        private var noteDatabase: NotesDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): NotesDatabase {
            if(noteDatabase == null){
                noteDatabase = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "notes_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return noteDatabase!!
        }
    }
}