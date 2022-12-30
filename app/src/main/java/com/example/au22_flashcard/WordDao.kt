package com.example.au22_flashcard

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(delete: Word)

    @Query("SELECT * FROM word_table")
    fun getAllWords() : List<Word>


}

// @Query("SELECT * FROM item_table")
//    fun getAll() : List<Item>
//
//    @Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
//    fun findByCategory(categoryName: String) : List<Item>