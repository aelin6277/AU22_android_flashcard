package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Transaction
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AddNewWord : AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    private lateinit var db : AppDatabase
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_word)
        job = Job()

        db = AppDatabase.getInstance(this)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val editTextEnglish = findViewById<EditText>(R.id.editTextEnglish)
            val englishWord: String = editTextEnglish.text.toString()

            val editTextSwedish = findViewById<EditText>(R.id.editTextSwedish)
            val swedishWord = editTextSwedish.text.toString()

            launch {
                async(Dispatchers.IO) {
                    db.wordDao.insert(Word(english = englishWord, swedish = swedishWord))
                }.await()
            }.invokeOnCompletion { finish() }
        }
    }
}