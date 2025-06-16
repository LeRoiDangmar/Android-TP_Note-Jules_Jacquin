package com.example.tp_flashcard.ui.flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState

@Composable
fun FlashcardScreen(
    categoryId: String,
    flashcardViewModel: FlashcardViewModel = viewModel(),
    onFinish: () -> Unit
) {
    LaunchedEffect(categoryId) { flashcardViewModel.loadCards(categoryId) }
    val uiState by flashcardViewModel.uiState.collectAsState()
    val current = uiState.currentFlashcard
    val scope = rememberCoroutineScope()
    val rotation = remember { Animatable(0f) }
    val density = LocalDensity.current.density

    LaunchedEffect(uiState.currentIndex) { rotation.snapTo(0f) }

    // Animation du progrès
    val targetProgress = if (uiState.flashcards.isNotEmpty())
        (uiState.currentIndex + 1) / uiState.flashcards.size.toFloat()
    else 0f
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (current != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 180.dp)
                        .graphicsLayer {
                            rotationY = rotation.value
                            cameraDistance = 8 * density
                        }
                        .clickable {
                            val target = if (rotation.value < 90f) 180f else 0f
                            scope.launch {
                                rotation.animateTo(target, tween(600))
                            }
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val front = rotation.value <= 90f
                        if (front) {
                            Text(
                                text = current.question,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        } else {
                            Box(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
                                Text(
                                    text = current.answer,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "Plus de cartes dans cette catégorie.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Button(
            onClick = { if (uiState.isFinished) onFinish() else flashcardViewModel.nextCard() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (uiState.isFinished) "Terminé" else "Suivant")
        }
    }
}
