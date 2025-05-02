package com.example.hindivocab.presentation

import androidx.lifecycle.ViewModel
import com.example.hindivocab.domain.usecase.VocabWordUseCases
import javax.inject.Inject

class VocabWordViewModel @Inject constructor(
    private val useCases: VocabWordUseCases
): ViewModel() {

}