package com.github.alfabravo2013.learnsyllables

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SyllablesAdapter(
    private val context: Context,
    private val clickListener: (CharSequence) -> Unit
) : RecyclerView.Adapter<SyllablesViewHolder>() {

    private val syllables: MutableList<Syllable> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyllablesViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.syllables_list_item_divided, parent, false)
        return SyllablesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SyllablesViewHolder, position: Int) {
        val hardConsonant = holder.itemView.findViewById<TextView>(R.id.hardConsonant)
        val hardVowel = holder.itemView.findViewById<TextView>(R.id.hardVowel)
        val softConsonant = holder.itemView.findViewById<TextView>(R.id.softConsonant)
        val softVowel = holder.itemView.findViewById<TextView>(R.id.softVowel)

        val syllable = syllables[position]

        hardConsonant.text = syllable.hardSyllable.first().toString()
        hardVowel.text = syllable.hardSyllable.last().toString()
        softConsonant.text = syllable.softSyllable.first().toString()
        softVowel.text = syllable.softSyllable.last().toString()

        hardConsonant.setOnLongClickListener {
            clickListener(syllable.hardSyllable)
            true
        }

        hardVowel.setOnLongClickListener {
            clickListener(syllable.hardSyllable)
            true
        }

        softConsonant.setOnLongClickListener {
            clickListener(syllable.softSyllable)
            true
        }

        softVowel.setOnLongClickListener {
            clickListener(syllable.softSyllable)
            true
        }
    }

    override fun getItemCount(): Int = syllables.size

    fun setData(newSyllables: List<Syllable>) {
        syllables.clear()
        syllables.addAll(newSyllables)
        notifyDataSetChanged()
    }
}