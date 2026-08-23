package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.data.models.ExamTrack
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                PanchayatApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun PanchayatApp(viewModel: MainViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val selectedTrack by viewModel.selectedTrack.collectAsState()
    val isBengali by viewModel.isBengaliLanguage.collectAsState()

    // During mock test, the MockTestScreen handles its own scaffold & timer app bar
    if (currentScreen == AppScreen.MOCK_TEST) {
        MockTestScreen(viewModel = viewModel)
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopHeader(
                currentScreen = currentScreen,
                selectedTrack = selectedTrack,
                isBengali = isBengali,
                onTrackToggle = {
                    val nextTrack = if (selectedTrack == ExamTrack.GENERAL) ExamTrack.NIRMAN_SAHAYAK else ExamTrack.GENERAL
                    viewModel.selectTrack(nextTrack)
                },
                onLanguageToggle = {
                    viewModel.toggleLanguage()
                },
                onBackClick = {
                    viewModel.navigateTo(AppScreen.HOME)
                },
                onOpenMistakes = {
                    viewModel.navigateTo(AppScreen.MISTAKE_BOOK)
                },
                onOpenBookmarks = {
                    viewModel.navigateTo(AppScreen.BOOKMARKS)
                }
            )
        },
        bottomBar = {
            AppBottomNavigation(
                currentScreen = currentScreen,
                onNavigate = { screen ->
                    viewModel.navigateTo(screen)
                }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        when (currentScreen) {
            AppScreen.HOME -> HomeScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.PRACTICE_HUB -> PracticeHubScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.GAP_ANALYSIS -> GapAnalysisScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.AI_TUTOR -> AiTutorScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.TEST_RESULT -> TestResultScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.MISTAKE_BOOK -> MistakeBookScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.BOOKMARKS -> BookmarksScreen(viewModel = viewModel, modifier = modifier)
            AppScreen.MOCK_TEST -> { /* Handled separately above */ }
        }
    }
}
