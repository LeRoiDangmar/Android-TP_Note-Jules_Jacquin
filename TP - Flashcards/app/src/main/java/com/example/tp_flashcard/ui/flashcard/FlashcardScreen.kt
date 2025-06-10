package com.example.tp_flashcard.ui.flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tp_flashcard.model.FlashCard

@Composable
fun FlashcardScreen(
    categoryId: String,
    flashcardViewModel: FlashcardViewModel = viewModel(),
    onFinish: () -> Unit
) {
    LaunchedEffect(categoryId) {
        flashcardViewModel.loadCards(categoryId)
    }

    val uiState by flashcardViewModel.uiState.collectAsState()

    var showAnswer by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        FlashcardProgress(currentIndex = uiState.currentIndex, total = uiState.flashcards.size)

        uiState.currentFlashcard?.let { card ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp)
                    .clickable { showAnswer = !showAnswer },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = if (showAnswer) card.answer else card.question,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        } ?: Text(
            text = "Aucune carte disponible.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (uiState.isFinished) {
                    onFinish()
                } else {
                    showAnswer = false
                    flashcardViewModel.onNextCard()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (uiState.isFinished) "Terminé" else "Suivant")
        }
    }
}

@Composable
private fun FlashcardProgress(currentIndex: Int, total: Int) {
    val progress = if (total > 0) (currentIndex + 1) / total.toFloat() else 0f
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
    )
}
