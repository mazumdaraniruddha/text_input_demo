package com.aniruddhamazumdar.cleartaxdemo

import java.util.*

class TextRepository {

    private var textValue: CharSequence? = ""
    private var currentWordCount: Int? = 0

    fun updateText(s: CharSequence?): CharSequence? {
        textValue = s
        return textValue
    }

    fun getCurrentWordCount(): Int? {
        currentWordCount = textValue
            ?.trim()
            ?.split(" ", ",", ".", "!")
            ?.filter {
                !it.isNullOrEmpty()
            }?.size
        return currentWordCount
    }

    fun handleUndoAction(): Pair<Int?, CharSequence?> {
        // Remove the last word from text and update text and count of words
        val words = textValue
            ?.trim()
            ?.split(" ")
        // Update the new text
        textValue = words?.take(words.size - 1)?.joinToString(" ")
        // Update the text count after undo
        getCurrentWordCount()
        // Return result
        return Pair(currentWordCount, textValue)
    }
}