package com.example.hindivocab.domain.usecase

import com.example.hindivocab.domain.repo.VocabWordRepo
import javax.inject.Inject

class GetAllSavedWordsUseCase @Inject constructor(
    private val repo: VocabWordRepo
) {
    operator fun invoke() = repo.getSavedWords()
}