package com.example.tp_flashcard.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.ui.home.HomeViewModel
import com.example.tp_flashcard.ui.flashcard.FlashcardScreen

@Composable
fun AppNavHost(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(homeViewModel) { category ->
                navController.navigate("flashcard/${category.id}")
            }
        }
        composable(
            "flashcard/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId").orEmpty()
            FlashcardScreen(categoryId) { navController.popBackStack() }
        }
    }
}