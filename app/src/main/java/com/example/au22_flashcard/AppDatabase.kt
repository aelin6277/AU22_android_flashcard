package com.example.au22_flashcard

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Word::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract val wordDao : WordDao
    abstract fun WordDao(): WordDao


        //SINGELTON
        private var INSTANCE : AppDatabase? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
                return INSTANCE!!
            }
        }
                    private fun buildRoomDB(context: Context) = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "word_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()






