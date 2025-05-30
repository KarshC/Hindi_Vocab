package com.example.hindivocab.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.presentation.PREVIEW_WORD
import com.example.hindivocab.utils.Utils

@Composable
fun FlipCardScreen(
    word: VocabWord,
    isFlipped: Boolean,
    showHinglish: Boolean,
    onCardClick: () -> Unit,
    onSaveToggle: () -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
    onHinglishToggle: () -> Unit,
    isAtFirst: Boolean,
    isAtLast: Boolean,
) {
    val baseCardColor = Utils().getCardColor(word.partOfSpeech)
    val backgroundColor = baseCardColor.copy(alpha = 0.5f)
    val cardColor = baseCardColor
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
                onSaveToggle = onSaveToggle,
                showHinglish = showHinglish,
                onHinglishToggle = onHinglishToggle
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedButton(
                    text = "Back",
                    onClick = onBack,
                    enabled = !isAtFirst,
                    color = cardColor
                )
                AnimatedButton(
                    text = "Next",
                    onClick = onNext,
                    enabled = !isAtLast,
                    color = cardColor
                )
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
        onBack = { },
        showHinglish = true,
        onHinglishToggle = { },
        isAtFirst = false,
        isAtLast = false
    )
}

