package com.example.hindivocab.presentation.views


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hindivocab.domain.model.PartOfSpeech
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.presentation.PREVIEW_WORD


@Composable
fun VocabCard(
    word: VocabWord,
    isFlipped: Boolean,
    cardColor: Color,
    onClick: () -> Unit,
    onSaveToggle: () -> Unit
) {
    val rotation = animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        label = "CardFlip"
    )

    val cameraDistance = 30f * LocalDensity.current.density

    Box(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .graphicsLayer {
                rotationY = rotation.value
                this.cameraDistance = cameraDistance
            }
            .clip(RoundedCornerShape(16.dp))
            .background(cardColor)
            .clickable { onClick() }
            .shadow(24.dp, RoundedCornerShape(16.dp), spotColor = cardColor.copy(alpha = 0.6f))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (rotation.value <= 90f) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = word.hindiWord,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = word.hinglishWord,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Text(
                text = word.meaning,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.graphicsLayer {
                    rotationY = 180f
                }
            )
        }

        IconButton(
            onClick = onSaveToggle,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-4).dp, y = 4.dp)
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = if (word.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (word.isSaved) "Saved" else "Save",
                tint = Color.Red
            )
        }
    }
}



fun getCardColor(partOfSpeech: PartOfSpeech?): Color {
    return when (partOfSpeech) {
        PartOfSpeech.NOUN -> Color(0xFFBBDEFB)       // Light Blue
        PartOfSpeech.VERB -> Color(0xFFA5D6A7)       // Light Green
        PartOfSpeech.ADJECTIVE -> Color(0xFFFFF59D)  // Light Yellow
        PartOfSpeech.ADVERB -> Color(0xFFE1BEE7)     // Light Purple
        PartOfSpeech.PREPOSITION -> Color(0xFFFFCCBC) // Light Orange
        PartOfSpeech.OTHER, null -> Color(0xFFD7CCC8) // Default Beige
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FlipCardPreview() {
    VocabCard(
        word = PREVIEW_WORD,
        isFlipped = false,
        cardColor = getCardColor(PREVIEW_WORD.partOfSpeech),
        onClick = {  },
        onSaveToggle = { },
    )
}

