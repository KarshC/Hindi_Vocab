package com.example.hindivocab.presentation

import com.example.hindivocab.domain.model.VocabWord

sealed class VocabUiState {
    object Loading: VocabUiState()
    data class Success(val data: List<VocabWord>): VocabUiState()
    data class Error(val message: String): VocabUiState()
}