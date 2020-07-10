package com.aniruddhamazumdar.cleartaxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aniruddhamazumdar.cleartaxdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVM()
        initTextWatcher()
        initUndoButton()
        setupObservers()
    }

    private fun initVM() {
        var viewModelFactory = ViewModelFactory(TextRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun initTextWatcher() {
        binding.etWordsInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateText(s)
            }
        })

        binding.etWordsInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                viewModel.calculateWordCount()
            }
        }
    }

    private fun initUndoButton() {
        binding.btnUndo.setOnClickListener {
            viewModel.handleUndoAction()
        }
    }

    private fun setupObservers() {
        viewModel.wordCountLiveData.observe(this, Observer {
            binding.tvWordCount.text = "Word Count: ${it ?: 0}"
        })

        viewModel.currentTextLiveData.observe(this, Observer {
            binding.etWordsInput.setText(it)
        })
    }
}