package com.example.hindivocab.domain.repo

import com.example.hindivocab.domain.model.VocabWord
import kotlinx.coroutines.flow.Flow

interface VocabWordRepo {
    fun getAllWords(): Flow<List<VocabWord>>
    fun getSavedWords(): Flow<List<VocabWord>>
    suspend fun getWordById(id: Int): VocabWord?
    suspend fun getRandomWord(): VocabWord?
    suspend fun insertWord(word: VocabWord)
    suspend fun insertWords(words: List<VocabWord>)
    suspend fun updateWord(word: VocabWord)
    suspend fun deleteWord(word: VocabWord)
    suspend fun loadWords()
    suspend fun syncWithJson()
}