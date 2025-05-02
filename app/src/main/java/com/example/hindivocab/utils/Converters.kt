package com.example.hindivocab.utils

import androidx.room.TypeConverter
import com.example.hindivocab.domain.model.PartOfSpeech

class Converters {

    @TypeConverter
    fun fromPartOfSpeech(value: PartOfSpeech): String = value.name

    @TypeConverter
    fun toPartOfSpeech(value: String): PartOfSpeech =
        PartOfSpeech.valueOf(value)
}