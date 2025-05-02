package com.example.hindivocab.domain.usecase

import com.example.hindivocab.domain.model.VocabWord
import com.example.hindivocab.domain.repo.VocabWordRepo
import javax.inject.Inject

class SaveWordUseCase @Inject constructor(
    private val repo: VocabWordRepo
) {
    suspend operator fun invoke(word: VocabWord) {
        repo.updateWord(word)
    }
}