package com.example.hindivocab.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hindivocab.data.local.dao.VocabWordDao
import com.example.hindivocab.data.local.entities.VocabWordEntity

@Database(
    entities = [VocabWordEntity::class],
    version = 3,
    exportSchema = false)
abstract class VocabDatabase: RoomDatabase() {
    abstract fun vocabWordDao(): VocabWordDao
}