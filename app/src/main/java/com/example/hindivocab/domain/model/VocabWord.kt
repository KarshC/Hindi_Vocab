package com.example.hindivocab.domain.model

data class VocabWord(
    val id: Int,
    val hindiWord: String,
    val meaning: String,
    val isSaved: Boolean,
    val partOfSpeech: PartOfSpeech,
)

enum class PartOfSpeech {
    NOUN,
    VERB,
    ADJECTIVE,
    ADVERB,
    PREPOSITION,
    OTHER
}
