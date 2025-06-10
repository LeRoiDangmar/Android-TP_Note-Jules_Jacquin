package com.example.tp_flashcard.ui.flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_flashcard.FlashcardRepository
import com.example.tp_flashcard.model.FlashCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlashcardViewModel : ViewModel() {

    // 1. État mutable privé
    private val _uiState = MutableStateFlow(FlashcardUiState())
    // 2. État public en lecture seule
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    /**
     * Charge les cartes pour la catégorie demandée.
     * À appeler dès l’arrivée sur l’écran de révision.
     */
    fun loadCards(categoryId: String) {
        viewModelScope.launch {
            // Récupère toutes les flashcards et filtre par catégorie
            val all = FlashcardRepository.getFlashcards()
            val filtered = all.filter { it.categoryId == categoryId }
            // Initialise l'état (currentIndex = 0, isFinished = filtered.isEmpty())
            _uiState.value = FlashcardUiState.initial(filtered)
        }
    }

    /**
     * Passe à la carte suivante.
     * Mettra à jour isFinished quand on aura dépassé la dernière carte.
     */
    fun onNextCard() {
        // Récupère l'état courant et génère le nouvel état
        val next = _uiState.value.nextState()
        _uiState.value = next
    }

    /**
     * Optionnel : recommencer la session depuis le début.
     */
    fun restart() {
        val cards = _uiState.value.flashcards
        _uiState.value = FlashcardUiState.initial(cards)
    }
}
