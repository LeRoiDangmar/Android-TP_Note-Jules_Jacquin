package com.example.tp_flashcard.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_flashcard.FlashcardRepository
import com.example.tp_flashcard.model.FlashCardCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FlashcardRepository = FlashcardRepository
) : ViewModel() {
    private val _categories = MutableStateFlow<List<FlashCardCategory>>(emptyList())
    val categories: StateFlow<List<FlashCardCategory>> = _categories.asStateFlow()

    init {
        viewModelScope.launch {
            _categories.value = repository.getCategories()
        }
    }
}