package com.example.hindivocab.domain.model

data class VocabWord(
    val id: Int,
    val hindiWord: String,
    val hinglishWord: String,
    val meaning: String,
    val isSaved: Boolean,
    val partOfSpeech: PartOfSpeech,
    val hasBeenShown: Boolean,
)

enum class PartOfSpeech {
    NOUN,
    VERB,
    ADJECTIVE,
    QUESTION,
    NUMBER,
    TIME,
    WEATHER,
    ADVERB,
    PREPOSITION,
    OTHER
}
