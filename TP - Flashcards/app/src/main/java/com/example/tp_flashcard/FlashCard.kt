package com.example.tp_flashcard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FlashcardScreen(
    categoryId: String,
) {
    // TODO: charger les flashcards de `categoryId`
    Text(text = "Écran de révision pour la catégorie : $categoryId")
}
