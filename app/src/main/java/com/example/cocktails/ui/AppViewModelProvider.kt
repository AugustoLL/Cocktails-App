package com.example.cocktails.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cocktails.CocktailsApplication
import com.example.cocktails.ui.cocktail.entry.CocktailEntryViewModel
import com.example.cocktails.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(inventoryApplication().container.cocktailsRepository)
        }
        initializer {
            CocktailEntryViewModel(inventoryApplication().container.cocktailsRepository)
        }
    }
}
/**
 * Extension function to queries for [Application] object and returns an instance of
 * [CocktailsApplication].
 */
fun CreationExtras.inventoryApplication(): CocktailsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)