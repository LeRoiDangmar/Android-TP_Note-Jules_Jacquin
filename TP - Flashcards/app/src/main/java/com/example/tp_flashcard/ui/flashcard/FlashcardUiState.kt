package com.example.tp_flashcard.ui.flashcard

import com.example.tp_flashcard.model.FlashCard

data class FlashcardUiState(
    val currentIndex: Int = 0,
    val flashcards: List<FlashCard> = emptyList(),
    val isFinished: Boolean = false
) {
    val currentFlashcard: FlashCard?
        get() = flashcards.getOrNull(currentIndex)

    companion object {
        /** Crée l’état initial à partir de la liste de cartes */
        fun initial(cards: List<FlashCard>) = FlashcardUiState(
            currentIndex = 0,
            flashcards = cards,
            isFinished = cards.isEmpty()
        )
    }

    fun nextState(): FlashcardUiState {
        val nextIndex = currentIndex + 1
        val finished = nextIndex >= flashcards.size
        return copy(
            currentIndex = nextIndex,
            isFinished = finished
        )
    }
}
