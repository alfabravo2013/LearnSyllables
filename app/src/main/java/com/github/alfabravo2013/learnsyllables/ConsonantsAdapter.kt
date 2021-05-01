package com.github.alfabravo2013.learnsyllables

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConsonantsAdapter(
    private val context: Context,
    private val consonants: List<Char>,
    private val callback: () -> Unit
) :
    RecyclerView.Adapter<ConsonantsViewHolder>() {

    private var currentPosition = 0
    val position: Int
        get() = currentPosition

    init {
        currentPosition = RecyclerView.NO_POSITION
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsonantsViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.consonant_list_item, parent, false)
        return ConsonantsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsonantsViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.tvConsonant)
        textView.text = consonants[position].toUpperCase().toString()

        holder.itemView.setOnClickListener {
            currentPosition = position
            callback()
        }
    }

    override fun getItemCount(): Int = consonants.size

}