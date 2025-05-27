package com.example.hindivocab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.domain.usecase.VocabWordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabViewModel @Inject constructor(
    private val useCases: VocabWordUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(VocabUiState())
    val uiState: StateFlow<VocabUiState> = _uiState

    private var wordList: List<VocabWord> = emptyList()
    private var currentIndex = 0

    init {
        loadWords()
        savedWords()
    }

    private fun loadWords() {
        viewModelScope.launch {
            useCases.initializeWordsUseCase()

            useCases.getAllWordsUseCase().collect { words ->
                wordList = words.shuffled()
                _uiState.value = _uiState.value.copy(
                    currentWord = wordList.getOrNull(currentIndex),
                    isLoading = false
                )
            }
        }
    }

    private fun savedWords() {
        viewModelScope.launch {
            useCases.getAllSavedWordsUseCase().collect { words ->
                _uiState.value = _uiState.value.copy(savedWords = words)
            }
        }
    }

    fun setScreen(screen: Screen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    fun onFlipCard() {
        _uiState.value = _uiState.value.copy(isFlipped = !_uiState.value.isFlipped)
    }

    fun onNextWord() {
        if (currentIndex < wordList.size - 1) {
            currentIndex++
            _uiState.value = _uiState.value.copy(
                currentWord = wordList[currentIndex],
                isFlipped = false
            )
        }
    }

    fun onPreviousWord() {
        if (currentIndex > 0) {
            currentIndex--
            _uiState.value = _uiState.value.copy(
                currentWord = wordList[currentIndex],
                isFlipped = false
            )
        }
    }

    fun onToggleSave() {
        _uiState.value.currentWord?.let { word ->
            viewModelScope.launch {
                val updated = word.copy(isSaved = !word.isSaved)
                useCases.saveWordUseCase(updated)
                wordList = wordList.toMutableList().apply {
                    this[currentIndex] = updated
                }
                _uiState.value = _uiState.value.copy(currentWord = updated)
            }
        }
    }
}
