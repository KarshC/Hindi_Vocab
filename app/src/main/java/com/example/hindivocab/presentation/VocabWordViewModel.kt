package com.example.hindivocab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindivocab.domain.model.PartOfSpeech
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
            useCases.syncWithJsonUseCase() // Include if needed

            useCases.getAllWordsUseCase().collect { words ->
                if (wordList.isEmpty()) {
                    val currentFilter = _uiState.value.selectedFilter
                    val filtered = currentFilter?.let { f -> words.filter { it.partOfSpeech == f } } ?: words

                    wordList = filtered.shuffled()
                    _uiState.value = _uiState.value.copy(
                        currentWord = wordList.getOrNull(currentIndex),
                        isLoading = false,
                        allWords = words
                    )
                } else {
                    _uiState.value = _uiState.value.copy(allWords = words)
                }

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
        applyFilterToCurrentScreen()
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

    fun onToggleSave(word: VocabWord) {
        viewModelScope.launch {
            val updated = word.copy(isSaved = !word.isSaved)
            useCases.saveWordUseCase(updated)

            // If this is the currentWord, update it too
            if (_uiState.value.currentWord?.id == updated.id) {
                _uiState.value = _uiState.value.copy(currentWord = updated)
            }

            // Force update savedWords list for immediate UI feedback
            val updatedSaved = if (updated.isSaved) {
                _uiState.value.savedWords + updated
            } else {
                _uiState.value.savedWords.filterNot { it.id == updated.id }
            }

            val updatedAll = _uiState.value.allWords.map {
                if (it.id == updated.id) updated else it
            }

            _uiState.value = _uiState.value.copy(
                savedWords = updatedSaved,
                allWords = updatedAll
            )
        }
    }

    fun setFilter(filter: PartOfSpeech?) {
        _uiState.value = _uiState.value.copy(selectedFilter = filter)
        applyFilterToCurrentScreen()
    }

    private fun applyFilterToCurrentScreen() {
        val filter = _uiState.value.selectedFilter
        val source = when (_uiState.value.currentScreen) {
            Screen.SAVED -> _uiState.value.savedWords
            Screen.All -> _uiState.value.allWords
            else -> _uiState.value.allWords
        }

        val filtered = if (filter != null) {
            source.filter { it.partOfSpeech == filter }
        } else {
            source
        }

        _uiState.value = _uiState.value.copy(filteredWords = filtered)

        if (_uiState.value.currentScreen == Screen.MAIN) {
            wordList = filtered.shuffled()
            currentIndex = 0
            _uiState.value = _uiState.value.copy(currentWord = wordList.getOrNull(currentIndex))
        }
    }

    fun toggleHinglish() {
        _uiState.value = _uiState.value.copy(showHinglish = !_uiState.value.showHinglish)
    }
}
