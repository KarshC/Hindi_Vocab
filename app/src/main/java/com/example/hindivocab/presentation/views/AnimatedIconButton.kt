package com.example.hindivocab.presentation.views


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (clicked) 1.4f else 1f,
        label = "IconScale"
    )

    LaunchedEffect(clicked) {
        if (clicked) {
            kotlinx.coroutines.delay(150)
            clicked = false
        }
    }

    IconButton(
        onClick = {
            clicked = true
            onClick()
        },
        modifier = modifier.graphicsLayer(scaleX = scale, scaleY = scale)
    ) {
        Icon(
            modifier = modifier.size(32.dp),
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

