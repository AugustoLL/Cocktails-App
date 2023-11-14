package com.example.cocktails.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cocktails.ui.cocktail.entry.CocktailEntryDestination
import com.example.cocktails.ui.cocktail.entry.CocktailEntryScreen
import com.example.cocktails.ui.home.HomeDestination
import com.example.cocktails.ui.home.HomeScreen

@Composable
fun CocktailsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCocktailEntry = {navController.navigate(CocktailEntryDestination.route)},
                navigateToCocktailUpdate = {}
            )
        }
        composable(route = CocktailEntryDestination.route) {
            CocktailEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
//        composable(route = CocktailDetailsDestination.route)
    }
}