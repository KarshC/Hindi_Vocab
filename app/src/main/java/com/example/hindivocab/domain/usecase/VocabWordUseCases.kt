package com.example.hindivocab.domain.usecase

data class VocabWordUseCases(
    val getAllWordsUseCase: GetAllWordsUseCase,
    val saveWordUseCase: SaveWordUseCase,
    val initializeWordsUseCase: InitializeWordsUseCase,
    val syncWithJsonUseCase: SyncWithJsonUseCase,
    val getAllSavedWordsUseCase: GetAllSavedWordsUseCase
)
