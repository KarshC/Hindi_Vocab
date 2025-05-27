package com.example.hindivocab.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hindivocab.data.local.entities.VocabWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabWordDao {

    @Query("SELECT * FROM vocab_words ORDER BY id ASC")
    fun getAllWords(): Flow<List<VocabWordEntity>>

    @Query("SELECT * FROM vocab_words WHERE id = :id")
    suspend fun getWordById(id: Int): VocabWordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: VocabWordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<VocabWordEntity>)

    @Update
    suspend fun updateWord(word: VocabWordEntity)

    @Delete
    suspend fun deleteWord(word: VocabWordEntity)

    @Query("SELECT * FROM vocab_words WHERE isSaved = 1")
    fun getSavedWords(): Flow<List<VocabWordEntity>>

    @Query("SELECT * FROM vocab_words ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(): VocabWordEntity?

    @Query("SELECT COUNT(*) FROM vocab_words")
    suspend fun countWords(): Int

    @Query("SELECT * FROM vocab_words")
    suspend fun getAllWordsOnce(): List<VocabWordEntity>

}