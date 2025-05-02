package com.example.hindivocab.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.presentation.PREVIEW_WORD

@Composable
fun FlipCardScreen(
    word: VocabWord,
    isFlipped: Boolean,
    onCardClick: () -> Unit,
    onSaveToggle: () -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val backgroundColor = getCardColor(word.partOfSpeech).copy(alpha = 0.5f)
    val cardColor = getCardColor(word.partOfSpeech)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            VocabCard(
                word = word,
                isFlipped = isFlipped,
                cardColor = cardColor,
                onClick = onCardClick,
                onSaveToggle = onSaveToggle
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = cardColor)
                ) {
                    Text("Back", color = Color.Black, style = MaterialTheme.typography.headlineSmall)
                }
                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(containerColor = cardColor)
                ) {
                    Text("Next", color = Color.Black, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlipCardScreen() {
    FlipCardScreen(
        word = PREVIEW_WORD,
        isFlipped = false,
        onCardClick = {},
        onSaveToggle = {},
        onNext = { },
        onBack = { }
    )
}

