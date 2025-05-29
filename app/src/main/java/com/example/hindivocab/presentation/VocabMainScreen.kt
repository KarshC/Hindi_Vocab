package com.example.hindivocab.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.hindivocab.presentation.views.FlipCardScreen
import com.example.hindivocab.presentation.views.WordsListScreen

@Composable
fun VocabMainScreen(viewModel: VocabViewModel) {
    val state by viewModel.uiState.collectAsState()

    when {
        state.isLoading -> LoadingScreen()
        state.error != null -> ErrorScreen(state.error ?: "Unknown Error")
        else -> when (state.currentScreen) {
            Screen.MAIN -> {
                state.currentWord?.let {
                    FlipCardScreen(
                        word = it,
                        isFlipped = state.isFlipped,
                        onCardClick = { viewModel.onFlipCard() },
                        onSaveToggle = { viewModel.onToggleSave(it) },
                        onNext = { viewModel.onNextWord() },
                        onBack = { viewModel.onPreviousWord() }
                    )
                }
                if (state.currentWord == null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No words available", style = MaterialTheme.typography.headlineSmall)
                    }
                }
            }

            Screen.SAVED -> {
                if (state.filteredWords.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No saved words match this filter.")
                    }
                } else {
                    WordsListScreen(
                        words = state.filteredWords,
                        savedWords = true,
                        onToggleSave = { viewModel.onToggleSave(it) }
                    )
                }
            }

            Screen.All -> {
                if (state.filteredWords.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No words match this filter.")
                    }
                } else {
                    WordsListScreen(
                        words = state.filteredWords,
                        savedWords = true,
                        onToggleSave = { viewModel.onToggleSave(it) }
                    )
                }
            }

        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Loading...", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error: $message", style = MaterialTheme.typography.headlineMedium, color = Color.Red)
    }
}
