package com.example.hindivocab.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hindivocab.domain.model.PartOfSpeech

@Entity(tableName = "vocab_words")
data class VocabWordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hindiWord: String?,
    val highlishWord: String?,
    val meaning: String?,
    val isSaved: Boolean?,
    val partOfSpeech: PartOfSpeech,
    val hasBeenShown: Boolean?
)
