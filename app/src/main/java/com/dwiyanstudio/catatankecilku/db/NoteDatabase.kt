package com.dwiyanstudio.catatankecilku.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataNote::class],version = 1)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun noteDao() : NoteDao
    companion object {
         private var INSTANCE : NoteDatabase? = null
        fun getInstance(context: Context) : NoteDatabase? {
            if(INSTANCE == null){
                synchronized(NoteDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"notedatabase.db").build()

                }
            }
            return INSTANCE

        }


    }

}