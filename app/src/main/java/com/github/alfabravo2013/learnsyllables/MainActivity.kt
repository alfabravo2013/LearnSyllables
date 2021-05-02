package com.github.alfabravo2013.learnsyllables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var rvSyllables: RecyclerView
    private lateinit var rvConsonants: RecyclerView

    private lateinit var tts: TextToSpeech

    private val viewModel: SyllableViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val consonantsAdapter = ConsonantsAdapter(this, viewModel)
        val syllablesAdapter = SyllablesAdapter(this) { speakOut(it) }

        rvSyllables = findViewById(R.id.rvSyllables)
        rvConsonants = findViewById(R.id.rvConsonants)

        rvConsonants.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvSyllables.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        LinearSnapHelper().attachToRecyclerView(rvConsonants)

        rvConsonants.adapter = consonantsAdapter
        rvSyllables.adapter = syllablesAdapter

        tts = TextToSpeech(this, this)

        viewModel.syllables.observe(this) {
            syllablesAdapter.setData(it)
        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale("ru"))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initialization failed!")
        }
    }

    private fun speakOut(text: CharSequence) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

}