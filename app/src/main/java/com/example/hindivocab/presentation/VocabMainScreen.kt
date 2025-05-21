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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hindivocab.presentation.views.FlipCardScreen

@Composable
fun VocabMainScreen(viewModel: VocabViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    // Show loading or error
    when {
        uiState.isLoading -> {
            LoadingScreen()
        }

        uiState.error != null -> {
            ErrorScreen(message = uiState.error!!)
        }

        uiState.currentWord != null -> {
            FlipCardScreen(
                word = uiState.currentWord!!,
                isFlipped = uiState.isFlipped,
                onCardClick = { viewModel.onFlipCard() },
                onSaveToggle = { viewModel.onToggleSave() },
                onNext = { viewModel.onNextWord() },
                onBack = { viewModel.onPreviousWord() }
            )
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

@Preview(showBackground = true)
@Composable
fun PreviewVocabMainScreen() {
    VocabMainScreen()
}
