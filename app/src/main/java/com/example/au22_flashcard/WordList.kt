package com.example.au22_flashcard

import android.content.Context
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WordList(val db: AppDatabase) : CoroutineScope {
    private val wordList = mutableListOf<Word>()
    private val usedWords = mutableListOf<Word>()

    private var job : Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun loadAllWords() {
        //lets make sure that the list is empty, so after adding a new word we can re initialize the wordlist
        wordList.clear()

        //Lets keep the harcoded words in case the DB is empty
        wordList.add(Word(english = "Hello", swedish = "Hej"))
        wordList.add(Word(english ="Good bye", swedish = "Hej då"))

      //getting words from database
       launch {
           val wordListFromDB = getAllWordsFromDB().await()
           //adding words from the DB to the hardocoded List
           wordList.addAll(wordListFromDB)
        }
    }

    fun getAllWordsFromDB(): Deferred<List<Word>> {
       return async(Dispatchers.IO) {
         db.wordDao.getAllWords()
        }
    }

    fun getNewWord(): Word {
        //if all words were revealed then the usedWord List is cleared
        if (wordList.isEmpty()) {
            Log.d("!!!", "All words have been seen, resetting lists!")
            // all words have been seen, lets clean used word and reset wordlist
            loadAllWords()
            usedWords.clear()
        }

        val rnd = (0 until wordList.size).random()
        var word = wordList[rnd]
        word.isUsed = true

        wordList.removeAt(rnd)//removing the word from the list, given it was already used
        usedWords.add(word) // adding the word to used list

        Log.d("!!!", "New word: $word. Remaining words: ${wordList.size}. Used words: ${usedWords.size}")
        return word
    }


    // 1. en till lista med redan använda ord -> klar
    // 2. lista med index på använda ord -> klar
    // 3. använt ord tas bort från listan -> klar
    // 4. ordet håller reda på om det redan är använt -> klar

    // 1. another list of already used words
    // 2. list with index of used words
    // 3. used word is removed from the list
    // 4. the word keeps track of whether it is already used

    //   val list = loadAllWords()
    //
    //    launch {
    //       val wordList = list.await()
    //    }
}



