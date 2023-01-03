package com.example.au22_flashcard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {

    lateinit var wordView: TextView
    var currentWord: Word? = null

    lateinit var wordList: WordList
    private lateinit var db : AppDatabase

    val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            //this will be executed after addword is finished
            wordList.loadAllWords()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)
        wordList = WordList(db)

        wordList.loadAllWords()

        wordView = findViewById(R.id.wordTextView)
        wordView.setOnClickListener {
            revealTranslation()
        }

        showNewWord()

        val button = findViewById<Button>(R.id.addWordButton)
        button.setOnClickListener {
            val intent = Intent(this, AddNewWord::class.java)
            intentLauncher.launch(intent)
        }
    }

    fun showNewWord() {
        currentWord = wordList.getNewWord()
        wordView.text = currentWord?.swedish
    }

    fun revealTranslation() {
        wordView.text = currentWord?.english
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            showNewWord()
            Log.d("!!!", "Touch!")
        }
        return true
    }
//koppla med knapparna, göra det som står ovan
}

//Vad ska göras:
//1.Skapa en ny aktivitet där ett nytt ord får skrivas in
//2.Spara det nya ordet i databasen.
//3.I main activity läs in alla ord från databasen.
//(använd coroutin när ni läser och skriver till databasen, se tidigare exempel)

//What to do:
//1. Create a new activity where a new word can be entered
//2.Save the new word in the database.
//3.In the main activity, read in all the words from the database.
//(use coroutine when you read and write to the database, see previous example)