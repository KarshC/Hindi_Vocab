package com.example.hindivocab.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.hindivocab.data.local.dao.VocabWordDao
import com.example.hindivocab.data.local.database.VocabDatabase
import com.example.hindivocab.data.remote.HindiWordsDataSource
import com.example.hindivocab.data.repoImpl.VocabWordRepoImpl
import com.example.hindivocab.domain.repo.VocabWordRepo
import com.example.hindivocab.domain.usecase.GetAllSavedWordsUseCase
import com.example.hindivocab.domain.usecase.GetAllWordsUseCase
import com.example.hindivocab.domain.usecase.InitializeWordsUseCase
import com.example.hindivocab.domain.usecase.SaveWordUseCase
import com.example.hindivocab.domain.usecase.SyncWithJsonUseCase
import com.example.hindivocab.domain.usecase.VocabWordUseCases
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): VocabDatabase =
        Room.databaseBuilder(app, VocabDatabase::class.java, "vocab_db")
            /*
            TODO: Update this to correctly migrate
             */
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideDao(db: VocabDatabase): VocabWordDao = db.vocabWordDao()

    @Provides
    @Singleton
    fun provideRepository(dao: VocabWordDao, dataSource: HindiWordsDataSource): VocabWordRepo = VocabWordRepoImpl(dao, dataSource)

    @Provides
    @Singleton
    fun provideVocabUseCases(repository: VocabWordRepo): VocabWordUseCases {
        return VocabWordUseCases(
            getAllWordsUseCase = GetAllWordsUseCase(repository),
            saveWordUseCase = SaveWordUseCase(repository),
            initializeWordsUseCase = InitializeWordsUseCase(repository),
            syncWithJsonUseCase = SyncWithJsonUseCase(repository),
            getAllSavedWordsUseCase = GetAllSavedWordsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideHindiWordsDataSource(
        @ApplicationContext context: Context,
        gson: Gson
    ): HindiWordsDataSource {
        return HindiWordsDataSource(context, gson)
    }
}