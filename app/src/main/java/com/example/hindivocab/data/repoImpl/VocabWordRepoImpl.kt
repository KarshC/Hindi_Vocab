package com.example.hindivocab.data.repoImpl

import com.example.hindivocab.data.local.dao.VocabWordDao
import com.example.hindivocab.data.mapper.toDomain
import com.example.hindivocab.data.mapper.toEntity
import com.example.hindivocab.data.remote.HindiWordsDataSource
import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.domain.repo.VocabWordRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VocabWordRepoImpl @Inject constructor(
    private val dao: VocabWordDao,
    private val dataSource: HindiWordsDataSource
): VocabWordRepo {
    override fun getAllWords(): Flow<List<VocabWord>> =
        dao.getAllWords().map { list -> list.map { it.toDomain() } }

    override fun getSavedWords(): Flow<List<VocabWord>> =
        dao.getSavedWords().map { list -> list.map { it.toDomain() } }

    override suspend fun getWordById(id: Int): VocabWord? =
        dao.getWordById(id)?.toDomain()

    override suspend fun getRandomWord(): VocabWord? =
        dao.getRandomWord()?.toDomain()

    override suspend fun insertWord(word: VocabWord) {
        dao.insertWord(word.toEntity())
    }

    override suspend fun insertWords(words: List<VocabWord>) {
        dao.insertWords(words.map { it.toEntity() })
    }

    override suspend fun updateWord(word: VocabWord) {
        dao.updateWord(word.toEntity())
    }

    override suspend fun deleteWord(word: VocabWord) {
        dao.deleteWord(word.toEntity())
    }

    override suspend fun loadWords() {
        val count = dao.countWords()
        if (count == 0) {
            val words = dataSource.loadWords()
            dao.insertWords(words.map { it.toEntity() })
        }
    }

    override suspend fun syncWithJson() {
        val existingWords = dao.getAllWordsOnce()
        val jsonWords = dataSource.loadWords()
        val existingSet = existingWords.map { it.hindiWord to it.meaning }.toSet()
        val newWords = jsonWords.filterNot { (existingSet.contains(it.hindiWord to it.meaning)) }

        if (newWords.isNotEmpty()) {
            dao.insertWords(newWords.map { it.toEntity() })
        }

    }
}