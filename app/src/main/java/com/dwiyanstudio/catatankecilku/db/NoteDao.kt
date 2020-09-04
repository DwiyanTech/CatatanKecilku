package com.dwiyanstudio.catatankecilku.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface  NoteDao {
@Query("select * from note")
 fun getAllNote(): List<DataNote>

@Delete
fun deleteNote(dataNote: DataNote)

@Update
fun editNote(dataNote: DataNote)

@Insert(onConflict = REPLACE)
fun insertNote(dataNote: DataNote)

@Query("select * from note where favourite = :isFavourite")
fun getFavNote(isFavourite : Boolean): List<DataNote>


}