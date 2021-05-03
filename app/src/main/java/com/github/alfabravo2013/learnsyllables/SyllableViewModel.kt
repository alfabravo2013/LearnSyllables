package com.github.alfabravo2013.learnsyllables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SyllableViewModel : ViewModel() {
    val syllables: MutableLiveData<List<Syllable>> = MutableLiveData(emptyList()) // todo check with the client if empty list is ok

    private var currentConsonant = DataStore.getDefaultChar()

    fun updateCurrentChar(char: Char) {
        currentConsonant = char
    }

    fun updateData() {
        syllables.value = DataStore.getSyllablesByChar(currentConsonant)
    }
}