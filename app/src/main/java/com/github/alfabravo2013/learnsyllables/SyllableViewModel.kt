package com.github.alfabravo2013.learnsyllables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SyllableViewModel : ViewModel() {
    val syllables: MutableLiveData<List<Syllable>> = MutableLiveData(DataStore.getDefaultSyllables())

    fun updateData(char: Char) {
        syllables.value = DataStore.getSyllablesByChar(char)
    }
}