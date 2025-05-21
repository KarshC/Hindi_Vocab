package com.example.hindivocab.data.mapper

import com.example.hindivocab.data.local.entities.VocabWordEntity
import com.example.hindivocab.domain.model.PartOfSpeech
import com.example.hindivocab.domain.model.VocabWord

fun VocabWordEntity.toDomain(): VocabWord =
    VocabWord(
        id = id,
        hindiWord = hindiWord ?: "",
        meaning = meaning ?: "",
        isSaved = isSaved == true,
        partOfSpeech = partOfSpeech ?: PartOfSpeech.OTHER,
        hasBeenShown = hasBeenShown == true,
    )

fun VocabWord.toEntity(): VocabWordEntity =
    VocabWordEntity(id, hindiWord, meaning, isSaved, partOfSpeech, hasBeenShown)