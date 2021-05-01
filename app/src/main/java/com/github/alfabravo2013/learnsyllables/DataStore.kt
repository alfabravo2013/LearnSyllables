package com.github.alfabravo2013.learnsyllables

class Syllable(val hardSyllable: String, val softSyllable: String)

object DataStore {
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

    fun getDefaultChar(): Char = consonants.first()

    fun getConsonants(): List<Char> = consonants

    fun getSyllablesByChar(char: Char): List<Syllable> = syllables[char] ?: emptyList()

    fun getConsonantByIndex(index: Int): Char = consonants[index]
}