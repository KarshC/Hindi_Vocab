package com.example.hindivocab.domain.usecase

import com.example.hindivocab.domain.repo.VocabWordRepo
import javax.inject.Inject

class InitializeWordsUseCase @Inject constructor(
    private val vocabWordRepo: VocabWordRepo
) {
    suspend operator fun invoke() = vocabWordRepo.loadWords()
}