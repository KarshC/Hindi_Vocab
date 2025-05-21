package com.example.hindivocab.presentation

import com.example.hindivocab.domain.model.VocabWord

data class VocabUiState(
    val currentWord: VocabWord? = null,
    val isFlipped: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)