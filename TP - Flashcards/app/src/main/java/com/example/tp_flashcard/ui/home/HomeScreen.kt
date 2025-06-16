package com.example.tp_flashcard.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp_flashcard.model.FlashCardCategory


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onCategoryClick: (FlashCardCategory) -> Unit
) {
    val categories by homeViewModel.categories.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(25.dp),

        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        items(categories) { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategoryClick(category) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }
}