package com.example.au22_flashcard

class WordList() {
    private val wordList = mutableListOf<Word>()
    private val usedWords = mutableListOf<Word>()

    init {
        initializeWords()
    }


    fun initializeWords() {
        wordList.add(Word(english = "Hello", swedish = "Hej"))
        wordList.add(Word(english ="Good bye", swedish = "Hej då"))
        wordList.add(Word(english ="Thank you", swedish = "Tack"))
        wordList.add(Word(english ="Welcome", swedish =  "Välkommen"))
        wordList.add(Word(english ="Computer", swedish = "Dator"))

        //TODO read all words from DB and add 'em to wordList

    }

    fun getNewWord(): Word {
        if (wordList.size == usedWords.size) {
            usedWords.clear()
        }

        var word: Word? = null

        do {
            val rnd = (0 until wordList.size).random()
            word = wordList[rnd]
        } while (usedWords.contains(word))

        usedWords.add(word!!)

        return word
    }


    // 1. en till lista med redan använda ord
    // 2. lista med index på använda ord
    // 3. använt ord tas bort från listan
    // 4. ordet håller reda på om det redan är använt

    // 1. another list of already used words
    // 2. list with index of used words
    // 3. used word is removed from the list
    // 4. the word keeps track of whether it is already used
}



