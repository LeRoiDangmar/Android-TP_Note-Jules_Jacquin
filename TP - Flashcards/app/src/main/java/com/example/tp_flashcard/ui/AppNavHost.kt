package com.example.tp_flashcard.ui

import androidx.compose.runtime.Composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.ui.home.HomeViewModel
import com.example.tp_flashcard.ui.flashcard.FlashcardScreen

@Composable
fun AppNavHost(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                homeViewModel = homeViewModel,
                onCategoryClick = { category ->
                    navController.navigate("flashcard/${category.id}")
                }
            )
        }

        composable(
            route = "flashcard/{categoryId}",
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry
                .arguments
                ?.getString("categoryId")
                .orEmpty()

            FlashcardScreen(
                categoryId = categoryId,
                onFinish = { navController.popBackStack() }
            )
        }
    }
}
