package com.example.hindivocab.utils

import androidx.compose.ui.graphics.Color
import com.example.hindivocab.domain.model.PartOfSpeech

class Utils {
    fun getCardColor(partOfSpeech: PartOfSpeech?): Color {
        return when (partOfSpeech) {
            PartOfSpeech.NOUN -> Color(0xFF64B5F6)        // Better blue
            PartOfSpeech.VERB -> Color(0xFF81C784)        // Vibrant green
            PartOfSpeech.ADJECTIVE -> Color(0xFFFFD54F)   // Strong yellow
            PartOfSpeech.ADVERB -> Color(0xFFBA68C8)      // Medium purple
            PartOfSpeech.PREPOSITION -> Color(0xFFFF8A65) // Orange/coral
            PartOfSpeech.OTHER, null -> Color(0xFFBCAAA4) // Soft beige/gray
            PartOfSpeech.QUESTION -> Color(0xFF4DD0E1)     // Bright teal (easy to read, attention-grabbing)
            PartOfSpeech.NUMBER -> Color(0xFFA1887F)       // Muted brown (earthy, consistent with logic)
            PartOfSpeech.TIME -> Color(0xFF9575CD)         // Soft violet (subtle, calming for time context)
            PartOfSpeech.WEATHER -> Color(0xFF4FC3F7)      // Sky blue (evokes natural weather elements)

        }
    }
}
