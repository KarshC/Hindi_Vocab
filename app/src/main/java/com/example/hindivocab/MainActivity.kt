package com.example.hindivocab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hindivocab.presentation.VocabMainScreen
import com.example.hindivocab.ui.theme.HindiVocabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HindiVocabTheme {
                VocabMainScreen()
            }
        }
    }
}
