package com.example.hindivocab.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hindivocab.domain.model.PartOfSpeech
import com.example.hindivocab.utils.Utils

@Composable
fun FilterChipsRow(
    selectedFilter: PartOfSpeech?,
    onFilterSelected: (PartOfSpeech?) -> Unit
) {
    val options = listOf(null) + PartOfSpeech.entries
    val utils = remember { Utils() }

    LazyRow(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(options) { part ->
            val selected = selectedFilter == part
            val baseColor = utils.getCardColor(part)
            val selectedColor = baseColor.copy(alpha = 0.8f)
            val borderColor = baseColor.copy(alpha = 0.9f)

            FilterChip(
                selected = selected,
                onClick = { onFilterSelected(part) },
                label = {
                    Text(
                        text = part?.name?.lowercase()?.replaceFirstChar { it.uppercase() }
                            ?: "All",
                        color = if (selected) Color.White else baseColor
                    )
                },
                shape = RoundedCornerShape(50),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (selected) selectedColor else Color.White,
                    selectedContainerColor = selectedColor,
                    labelColor = if (selected) Color.White else baseColor,
                    selectedLabelColor = Color.White
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = borderColor,
                    enabled = true,
                    selected = selected
                )
            )
        }
    }
}
