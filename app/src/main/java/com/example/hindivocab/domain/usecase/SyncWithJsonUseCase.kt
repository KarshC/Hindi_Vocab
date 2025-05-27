package com.example.hindivocab.domain.usecase

import com.example.hindivocab.domain.repo.VocabWordRepo
import javax.inject.Inject

class SyncWithJsonUseCase @Inject constructor(
    private val repo: VocabWordRepo
) {
    suspend operator fun invoke() = repo.syncWithJson()
}