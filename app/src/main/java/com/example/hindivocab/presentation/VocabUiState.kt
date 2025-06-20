package com.example.hindivocab.presentation

import com.example.hindivocab.domain.model.PartOfSpeech
import com.example.hindivocab.domain.model.VocabWord

data class VocabUiState(
    val currentWord: VocabWord? = null,
    val isFlipped: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null,
    val savedWords: List<VocabWord> = emptyList(),
    val currentScreen: Screen = Screen.MAIN,
    val allWords: List<VocabWord> = emptyList(),
    val selectedFilter: PartOfSpeech? = null,
    val filteredWords: List<VocabWord> = emptyList(),
    val showHinglish: Boolean = true,
    val visibleHinglishWordIds: Set<Int> = emptySet(),
)

enum class Screen {
    MAIN,
    All,
    SAVED
}