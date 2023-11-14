package com.example.cocktails.ui.home

import androidx.lifecycle.ViewModel
import com.example.cocktails.data.CocktailsRepository
import androidx.lifecycle.viewModelScope
import com.example.cocktails.model.Cocktail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repository: CocktailsRepository) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        repository.getAllCocktailsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun deleteCocktail(cocktail: Cocktail) {
        repository.deleteCocktail(cocktail)
    }
}

data class HomeUiState(val cocktailsList: List<Cocktail> = listOf())