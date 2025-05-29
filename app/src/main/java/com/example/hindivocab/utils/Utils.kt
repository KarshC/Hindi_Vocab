package com.example.hindivocab.utils

import androidx.compose.ui.graphics.Color
import com.example.hindivocab.domain.model.PartOfSpeech

class Utils {
    fun getCardColor(partOfSpeech: PartOfSpeech?): Color {
        return when (partOfSpeech) {
            PartOfSpeech.NOUN -> Color(0xFF42A5F5)        // Vibrant blue
            PartOfSpeech.VERB -> Color(0xFF66BB6A)        // Fresh green
            PartOfSpeech.ADJECTIVE -> Color(0xFFFF7043)   // Deep orange
            PartOfSpeech.ADVERB -> Color(0xFFAB47BC)      // Rich purple
            PartOfSpeech.PREPOSITION -> Color(0xFFFFCA28) // Golden yellow
            PartOfSpeech.QUESTION -> Color(0xFF26C6DA)    // Bright cyan
            PartOfSpeech.NUMBER -> Color(0xFF536DFE)      // Electric indigo ✅
            PartOfSpeech.TIME -> Color(0xFF7E57C2)        // Medium violet
            PartOfSpeech.WEATHER -> Color(0xFF29B6F6)     // Sky blue
            PartOfSpeech.OTHER, null -> Color(0xFF8D6E63) // Warm taupe

        }
    }
}
