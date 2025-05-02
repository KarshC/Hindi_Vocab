package com.example.hindivocab.presentation

import com.example.hindivocab.domain.model.VocabWord

sealed class VocabUiEvent {
    data class SaveWord(val word: VocabWord): VocabUiEvent()
}