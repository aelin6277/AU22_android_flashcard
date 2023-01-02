package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.room.Room


class MainActivity : AppCompatActivity() {

    lateinit var wordView : TextView
    var currentWord : Word? = null
    val wordList = WordList()
    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordView = findViewById(R.id.wordTextView)


        fun showNewWord() {

            currentWord = wordList.getNewWord()
            wordView.text = currentWord?.swedish
        }
        showNewWord()

        fun revealTranslation() {
            wordView.text = currentWord?.english
        }
        wordView.setOnClickListener {
            revealTranslation()
        }


         fun onTouchEvent(event: MotionEvent?): Boolean {

            if (event?.action == MotionEvent.ACTION_UP) {
                showNewWord()
            }
            Log.d("!!!", "Touch!")
            return true
        }

            fun initializeWords() {
                val word = Word
            }


            val button = findViewById<Button>(R.id.addWordButton)
        button.setOnClickListener {
            val intent = Intent(this,AddNewWord::class.java)
            startActivity(intent)
            //TODO refresh wordList (you may clear the list and then call initializeWords())
        }
 }






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