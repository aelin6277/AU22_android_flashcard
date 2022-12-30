package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var wordView : TextView
    var currentWord : Word? = null
    val wordList = WordList()
    lateinit var db : AppDatabase
    lateinit var addWordButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)
        wordList.initializeWords()

        wordView = findViewById(R.id.wordTextView)
        showNewWord()
        wordView.setOnClickListener {
            revealTranslation()
        }

        val button = findViewById<Button>(R.id.addWordButton)
        button.setOnClickListener {
            val intent = Intent(this,AddNewWord::class.java)
            startActivity(intent)
        }
    }

    fun revealTranslation() {
        wordView.text = currentWord?.english
    }


    fun showNewWord() {

        currentWord = wordList.getNewWord()
        wordView.text = currentWord?.swedish
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {
            showNewWord()
        }

        return true
    }




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