package com.aniruddhamazumdar.cleartaxdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val repository: TextRepository): ViewModel() {

    val wordCountLiveData = MutableLiveData<Int?>(0)
    val currentTextLiveData = MutableLiveData<CharSequence?>()

    fun updateText(s: CharSequence?): CharSequence? {
        return repository.updateText(s)
    }

    fun calculateWordCount() {
        wordCountLiveData.value = repository.getCurrentWordCount()
    }

    fun handleUndoAction() {
        val textDataPair = repository.handleUndoAction()
        wordCountLiveData.value = textDataPair.first
        currentTextLiveData.value = textDataPair.second
    }
}