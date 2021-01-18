package com.bits.notes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
class Note: Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title:String = ""

    @ColumnInfo(name = "date_time")
    var dateTime:String = ""

    @ColumnInfo(name = "note_text")
    var noteText:String = ""

    @ColumnInfo(name = "image_path")
    var imagePath:String = ""

    @ColumnInfo(name = "color")
    var color:String = ""

    @ColumnInfo(name = "web_link")
    var webLink:String = ""
}







