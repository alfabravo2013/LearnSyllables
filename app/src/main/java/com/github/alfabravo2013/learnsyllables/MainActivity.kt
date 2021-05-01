package com.github.alfabravo2013.learnsyllables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvSyllables: RecyclerView
    private lateinit var rvConsonants: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val consonantsAdapter = ConsonantsAdapter(this, DataStore.getConsonants()) { update() }
        val syllablesAdapter = SyllablesAdapter(this, DataStore.getSyllablesByChar(DataStore.getDefaultChar()))

        rvSyllables = findViewById(R.id.rvSyllables)
        rvConsonants = findViewById(R.id.rvConsonants)

        rvConsonants.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvSyllables.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvConsonants.adapter = consonantsAdapter
        rvSyllables.adapter = syllablesAdapter

    }

    private fun update() {
        val cAdapter = rvConsonants.adapter as ConsonantsAdapter
        val sAdapter = rvSyllables.adapter as SyllablesAdapter

        val consonant = DataStore.getConsonantByIndex(cAdapter.position)
        sAdapter.setData(DataStore.getSyllablesByChar(consonant))
    }

}