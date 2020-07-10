package com.aniruddhamazumdar.cleartaxdemo.data

interface Storage {
    fun getText(): CharSequence?
    fun storeText(text: CharSequence?)
}