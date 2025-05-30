package com.example.hindivocab.presentation.views


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.presentation.PREVIEW_WORD
import com.example.hindivocab.utils.Utils


@Composable
fun VocabCard(
    word: VocabWord,
    isFlipped: Boolean,
    cardColor: Color,
    showHinglish: Boolean = true,
    onClick: () -> Unit,
    onSaveToggle: () -> Unit,
    onHinglishToggle: () -> Unit,
) {
    val rotation = animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "CardFlip"
    )

    val cameraDistance = 32f * LocalDensity.current.density

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
                if (showHinglish) {
                    Text(
                        text = word.hinglishWord,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = word.hindiWord,
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

        AnimatedIconButton(
            onClick = onSaveToggle,
            icon = if (word.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = if (word.isSaved) "Unsave" else "Save",
            tint = Color.Red,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-4).dp, y = 4.dp)
        )

        AnimatedIconButton(
            onClick = onHinglishToggle,
            icon = if (showHinglish) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = if (showHinglish) "Hide Hinglish" else "Show Hinglish",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-4).dp, y = (-4).dp)
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FlipCardPreview() {
    VocabCard(
        word = PREVIEW_WORD,
        isFlipped = false,
        cardColor = Utils().getCardColor(PREVIEW_WORD.partOfSpeech),
        onClick = { },
        onSaveToggle = { },
        showHinglish = true,
        onHinglishToggle = { },
    )
}

