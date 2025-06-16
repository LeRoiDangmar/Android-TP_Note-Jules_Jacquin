package com.example.tp_flashcard.ui.flashcard

import com.example.tp_flashcard.model.FlashCard

data class FlashcardUiState(
    val currentIndex: Int = 0,
    val flashcards: List<FlashCard> = emptyList(),
    val isFinished: Boolean = flashcards.isEmpty()
) {
    val currentFlashcard: FlashCard?
        get() = flashcards.getOrNull(currentIndex)

    fun next(): FlashcardUiState {
        val nextIndex = currentIndex + 1
        return copy(
            currentIndex = nextIndex,
            isFinished = nextIndex >= flashcards.size
        )
    }

    companion object {
        fun initial(cards: List<FlashCard>) = FlashcardUiState(
            currentIndex = 0,
            flashcards = cards,
            isFinished = cards.isEmpty()
        )
    }
}