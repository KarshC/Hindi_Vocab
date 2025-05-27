package com.example.hindivocab.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.utils.Utils

@Composable
fun WordsListScreen(
    words: List<VocabWord>,
    savedWords: Boolean = false,
    onToggleSave: (VocabWord) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(words) { word ->
            val cardColor = Utils().getCardColor(word.partOfSpeech)

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = word.hindiWord, style = MaterialTheme.typography.titleLarge)
                            Text(text = word.hinglishWord, style = MaterialTheme.typography.bodyMedium)
                            Text(
                                text = word.meaning.lowercase().replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = word.partOfSpeech.name.lowercase().replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if (savedWords) {
                            IconButton(onClick = { onToggleSave(word) }) {
                                val icon =
                                    if (word.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                                Icon(
                                    imageVector = icon,
                                    contentDescription = if (word.isSaved) "Unsave" else "Save",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}
