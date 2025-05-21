package com.example.hindivocab.data.remote

import android.content.Context
import com.example.hindivocab.domain.model.VocabWord
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class HindiWordsDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson,
) {
    suspend fun loadWords(): List<VocabWord> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("vocab_words.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<VocabWord>>() {}.type
            gson.fromJson(jsonString, type)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}