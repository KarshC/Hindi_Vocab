package com.example.hindivocab.data.remote

import android.content.Context
import com.example.hindivocab.domain.model.PartOfSpeech
import com.example.hindivocab.domain.model.VocabWord
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class HindiWordsDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) {
    suspend fun loadWords(): List<VocabWord> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("final_vocab_words.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<JsonVocabWord>>() {}.type
            val jsonList: List<JsonVocabWord> = gson.fromJson(jsonString, type)
            jsonList.mapIndexed { index, jsonWord ->
                VocabWord(
                    id = index,
                    hindiWord = jsonWord.hindiWord,
                    hinglishWord = jsonWord.hinglishWord,
                    meaning = jsonWord.meaning,
                    partOfSpeech = jsonWord.partOfSpeech,
                    isSaved = false,
                    hasBeenShown = false,
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

private data class JsonVocabWord(
    @SerializedName("hindiWord") val hindiWord: String,
    @SerializedName("hinglishWord") val hinglishWord: String,
    @SerializedName("meaning") val meaning: String,
    @SerializedName("partOfSpeech") val partOfSpeech: PartOfSpeech
)