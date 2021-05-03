package com.github.alfabravo2013.learnsyllables

// todo change to single syllables to allow switching to a grid layout
//  (2 col portrait, 4 col landscape). syllable may be nullable so that
//  List<Syllable> can contain nulls for non existing syllables
class Syllable(val hardSyllable: String, val softSyllable: String)

object DataStore {
    private const val NOT_EXIST = "--"

    private val vowelsHard = listOf('А', 'О', 'У', 'Ы', 'Э')
    private val vowelsSoft = listOf('Я', 'Ё', 'Ю', 'И', 'Е')
    private val consonants = listOf(
        'Б', 'В', 'Г', 'Д', 'Ж', 'З', 'К', 'Л', 'М', 'Н', 'П',
        'Р', 'С', 'Т', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ'
    )

    private val forbidden = mapOf(
        'Ж' to listOf('Я', 'Ы', 'Э'),
        'Ц' to listOf('Я', 'Ё', 'Э', 'Ю'),
        'Ч' to listOf('Ы', 'Э', 'Я', 'Ю'),
        'Ш' to listOf('Ы', 'Э', 'Я'),
        'Щ' to listOf('Ы', 'Э', 'Я', 'Ю')
    )

    private val syllables = consonants.map { consonant ->
        consonant to (0..vowelsHard.lastIndex).map { index ->
            val hardVowel = vowelsHard[index]
            val softVowel = vowelsSoft[index]

            val hardSyllable = if (forbidden[consonant]?.contains(hardVowel) == true) NOT_EXIST
            else "$consonant$hardVowel"

            val softSyllable = if (forbidden[consonant]?.contains(softVowel) == true) NOT_EXIST
            else "$consonant$softVowel"

            Syllable(hardSyllable, softSyllable)
        }.toList()
    }.toMap()

    fun getDefaultChar(): Char = consonants.first()

    fun getConsonants(): List<Char> = consonants

    fun getSyllablesByChar(char: Char): List<Syllable> = syllables[char] ?: emptyList()

    fun getConsonantByIndex(index: Int): Char = consonants[index]

    fun getDefaultSyllables(): List<Syllable> = getSyllablesByChar(consonants.first())
}