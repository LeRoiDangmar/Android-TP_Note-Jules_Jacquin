package com.example.tp_flashcard.ui.flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_flashcard.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlashcardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FlashcardUiState())
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    fun loadCards(categoryId: String) {
        viewModelScope.launch {
            val allCards = FlashcardRepository.getFlashcards()
            val categoryCards = allCards.filter { it.categoryId == categoryId }
            _uiState.value = FlashcardUiState.initial(categoryCards)
        }
    }

    fun nextCard() {
        _uiState.value = _uiState.value.next()
    }
}