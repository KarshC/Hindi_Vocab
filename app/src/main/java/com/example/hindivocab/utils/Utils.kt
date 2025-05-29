package com.example.hindivocab.utils

import androidx.compose.ui.graphics.Color
import com.example.hindivocab.domain.model.PartOfSpeech

class Utils {
    fun getCardColor(partOfSpeech: PartOfSpeech?): Color {
        return when (partOfSpeech) {
            PartOfSpeech.NOUN -> Color(0xFF64B5F6)       // Better blue
            PartOfSpeech.VERB -> Color(0xFF81C784)       // Vibrant green
            PartOfSpeech.ADJECTIVE -> Color(0xFFFFD54F)  // Strong yellow
            PartOfSpeech.ADVERB -> Color(0xFFBA68C8)     // Medium purple
            PartOfSpeech.PREPOSITION -> Color(0xFFFF8A65) // Orange/coral
            PartOfSpeech.OTHER, null -> Color(0xFFBCAAA4) // Soft beige/gray
        }
    }
}
