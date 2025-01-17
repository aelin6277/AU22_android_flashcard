package com.example.au22_flashcard

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "english") var english: String,
    @ColumnInfo(name = "swedish") var swedish: String,
    var isUsed: Boolean = false
) {
}