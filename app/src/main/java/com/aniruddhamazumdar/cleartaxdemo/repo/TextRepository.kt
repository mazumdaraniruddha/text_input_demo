package com.aniruddhamazumdar.cleartaxdemo.repo

import com.aniruddhamazumdar.cleartaxdemo.data.Storage

class TextRepository(private val storage: Storage) {

    private var textValue: CharSequence?
        get() {
            return storage.getText()
        }
        set(value) {
            storage.storeText(value)
        }

    fun getCurrentText() = textValue

    fun updateText(s: CharSequence?): CharSequence? {
        textValue = s
        return textValue
    }

    fun getCurrentWordCount(): Int? {
        return textValue
            ?.trim()
            ?.split(" ", ",", ".", "!")
            ?.filter {
                !it.isNullOrEmpty()
            }?.size
    }

    fun handleUndoAction(): Pair<Int?, CharSequence?> {
        // Remove the last word from text and update text and count of words
        val words = textValue
            ?.trim()
            ?.split(" ")
        // Update the new text
        textValue = words?.take(words.size - 1)?.joinToString(" ")
        // Return result
        return Pair(getCurrentWordCount(), textValue)
    }
}