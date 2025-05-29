package com.example.hindivocab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hindivocab.presentation.Screen
import com.example.hindivocab.presentation.VocabMainScreen
import com.example.hindivocab.presentation.VocabViewModel
import com.example.hindivocab.presentation.views.FilterChipsRow
import com.example.hindivocab.ui.theme.HindiVocabTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HindiVocabTheme {
                val viewModel: VocabViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsState()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            NavigationDrawerItem(
                                label = { Text("Learn Words") },
                                selected = state.currentScreen == Screen.MAIN,
                                onClick = {
                                    viewModel.setScreen(Screen.MAIN)
                                    scope.launch { drawerState.close() }
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("All Words") },
                                selected = state.currentScreen == Screen.All,
                                onClick = {
                                    viewModel.setScreen(Screen.All)
                                    scope.launch { drawerState.close() }
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Saved Words") },
                                selected = state.currentScreen == Screen.SAVED,
                                onClick = {
                                    viewModel.setScreen(Screen.SAVED)
                                    scope.launch { drawerState.close() }
                                }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Hindi Vocab") },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                                    }
                                },
                                modifier = Modifier.shadow(4.dp)
                            )
                        }
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            // ✅ Only show filter chips on MAIN screen
                            if (state.currentScreen in listOf(Screen.MAIN, Screen.SAVED, Screen.All)) {
                                FilterChipsRow(
                                    selectedFilter = state.selectedFilter,
                                    onFilterSelected = { viewModel.setFilter(it) }
                                )
                            }

                            // ✅ Main screen logic
                            VocabMainScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}


