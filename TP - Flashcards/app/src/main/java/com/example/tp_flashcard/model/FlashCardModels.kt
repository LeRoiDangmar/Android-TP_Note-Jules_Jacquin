package com.example.tp_flashcard.model

data class FlashCard(
    val id: String,
    val categoryId: String,
    val question: String,
    val answer: String
)
data class FlashCardCategory(
    val id: String,
    val name: String
)