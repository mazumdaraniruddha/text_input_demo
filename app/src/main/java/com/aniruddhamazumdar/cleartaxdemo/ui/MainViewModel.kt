package com.aniruddhamazumdar.cleartaxdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aniruddhamazumdar.cleartaxdemo.repo.TextRepository

class MainViewModel(private val repository: TextRepository): ViewModel() {

    val wordCountLiveData = MutableLiveData<Int?>(0)
    val currentTextLiveData = MutableLiveData<CharSequence?>()

    init {
        currentTextLiveData.value = repository.getCurrentText()
        wordCountLiveData.value = repository.getCurrentWordCount()
    }

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