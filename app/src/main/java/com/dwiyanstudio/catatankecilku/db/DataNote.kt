package com.dwiyanstudio.catatankecilku.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note")
data class DataNote(@ColumnInfo(name = "judul") var judul :String, @ColumnInfo(name = "isi") var isi :String, @ColumnInfo(name = "favourite") var isFavourite : String, @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id :Long = 0) :Parcelable