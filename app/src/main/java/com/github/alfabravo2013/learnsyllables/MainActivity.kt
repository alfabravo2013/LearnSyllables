package com.github.alfabravo2013.learnsyllables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvSyllables: RecyclerView
    private lateinit var rvConsonants: RecyclerView

    private val vowelsHard = listOf('а', 'о', 'у', 'ы', 'э')
    private val vowelsSoft = listOf('я', 'ё', 'ю', 'и', 'е')
    private val consonants = listOf(
        'б', 'в', 'г', 'д', 'ж', 'з', 'к', 'л', 'м', 'н', 'п',
        'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
    )

    private val forbidden = mapOf(
        'ж' to listOf('я', 'ы', 'э'),
        'ц' to listOf('я', 'ё', 'э', 'ю'),
        'ч' to listOf('ы', 'э', 'я', 'ю'),
        'ш' to listOf('ы', 'э', 'я'),
        'щ' to listOf('ы', 'э', 'я', 'ю')
    )

    private val syllables = consonants.map { consonant ->
        consonant to (0..vowelsHard.lastIndex).map { index ->
            val hardVowel = vowelsHard[index]
            val softVowel = vowelsSoft[index]

            val hardSyllable = if (forbidden[consonant]?.contains(hardVowel) == true) "--"
            else "$consonant$hardVowel"

            val softSyllable = if (forbidden[consonant]?.contains(softVowel) == true) "--"
            else "$consonant$softVowel"

            Syllable(hardSyllable, softSyllable)
        }.toList()
    }.toMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val consonantsAdapter = ConsonantsAdapter(this, consonants)
        val syllablesAdapter = SyllablesAdapter(this, syllables['б'] ?: emptyList())

        rvSyllables = findViewById(R.id.rvSyllables)
        rvConsonants = findViewById(R.id.rvConsonants)

        rvConsonants.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvSyllables.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvConsonants.adapter = consonantsAdapter
        rvSyllables.adapter = syllablesAdapter


    }

    private fun updateSyllablesList(adapter: SyllablesAdapter, index: Int) {
        val consonant = consonants[index]
        adapter.setData(syllables[consonant] ?: emptyList())
    }
}

class Syllable(val hardSyllable: String, val softSyllable: String)