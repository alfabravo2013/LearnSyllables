package com.github.alfabravo2013.learnsyllables

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class SyllablesAdapter(
    private val context: Context,
    private val clickListener: (CharSequence) -> Unit
) : RecyclerView.Adapter<SyllablesViewHolder>() {

    private val syllables: MutableList<Syllable> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyllablesViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.syllables_list_item, parent, false)
        return SyllablesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SyllablesViewHolder, position: Int) {
        val hardSyllable = holder.itemView.findViewById<TextView>(R.id.tvHardSyllable)
        val softSyllable = holder.itemView.findViewById<TextView>(R.id.tvSoftSyllable)
        val syllable = syllables[position]

        hardSyllable.text = syllable.hardSyllable.toUpperCase(Locale("ru"))
        softSyllable.text = syllable.softSyllable.toUpperCase(Locale("ru"))

        softSyllable.setOnLongClickListener {
            clickListener(softSyllable.text)
            true
        }

        hardSyllable.setOnLongClickListener {
            clickListener(hardSyllable.text)
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