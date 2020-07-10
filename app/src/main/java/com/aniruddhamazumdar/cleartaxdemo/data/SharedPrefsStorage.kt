package com.aniruddhamazumdar.cleartaxdemo.data

import android.content.Context

class SharedPrefsStorage(private val context: Context):
    Storage {

    private val prefs = context.getSharedPreferences("TextStore", Context.MODE_PRIVATE)

    override fun getText(): CharSequence? {
        return prefs.getString(TEXT_KEY, "")
    }

    override fun storeText(text: CharSequence?) {
        prefs.edit().putString(TEXT_KEY, text.toString()).apply()
    }

    companion object {
        private const val TEXT_KEY = "current_text"
    }
}