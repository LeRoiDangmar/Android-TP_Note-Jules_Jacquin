package com.example.tp_flashcard

import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.model.FlashCardCategory
object FlashcardRepository {
    val categories: List<FlashCardCategory> = listOf(
        FlashCardCategory(
            id = "history",
            name = "Histoire"
        ),
        FlashCardCategory(
            id = "science",
            name = "Sciences"
        ),
        FlashCardCategory(
            id = "geography",
            name = "Géographie"
        )
    )

    val flashcards: List<FlashCard> = listOf(
        // Histoire
        FlashCard(
            id = "h1",
            categoryId = "history",
            question = "Qui était le premier empereur romain ?",
            answer = "Auguste"
        ),
        FlashCard(
            id = "h2",
            categoryId = "history",
            question = "En quelle année la Révolution française a-t-elle commencé ?",
            answer = "1789"
        ),
        // Sciences
        FlashCard(
            id = "s1",
            categoryId = "science",
            question = "Quelle est la formule chimique de l'eau ?",
            answer = "H₂O"
        ),
        FlashCard(
            id = "s2",
            categoryId = "science",
            question = "Quelle planète est la troisième à partir du Soleil ?",
            answer = "La Terre"
        ),
        FlashCard(
            id = "g1",
            categoryId = "geography",
            question = "Quelle est la capitale de la France ?",
            answer = "Paris"
        ),
        FlashCard(
            id = "g2",
            categoryId = "geography",
            question = "Quel est le plus long fleuve du monde ?",
            answer = "Le Nil"
        )
    )

    suspend fun getCategories(): List<FlashCardCategory> {
        return categories
    }

    suspend fun getFlashcards(): List<FlashCard> = flashcards
}
