package com.example.hindivocab.utils

import androidx.compose.ui.graphics.Color
import com.example.hindivocab.domain.model.PartOfSpeech

class Utils {
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
}