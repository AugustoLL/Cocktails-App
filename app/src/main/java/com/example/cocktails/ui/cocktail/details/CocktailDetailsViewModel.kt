package com.example.cocktails.ui.cocktail.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.data.CocktailsRepository
import com.example.cocktails.ui.cocktail.entry.CocktailDetails
import com.example.cocktails.ui.cocktail.entry.CocktailUiState
import com.example.cocktails.ui.cocktail.entry.toCocktail
import com.example.cocktails.ui.cocktail.entry.toCocktailDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private const val TAG = "COCKTAIL_DETAILS_VM"

class CocktailDetailsViewModel(
    private val repository: CocktailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cocktailId: Int =
        checkNotNull(savedStateHandle[CocktailDetailsDestination.cocktailIdArg])

    val uiState: StateFlow<CocktailDetailsUiState> =
        repository.getCocktailStream(cocktailId)
            .filterNotNull()
            .map {
                CocktailDetailsUiState(it.toCocktailDetails())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = CocktailDetailsUiState()
            )

    suspend fun deleteCocktail() {
        repository.deleteCocktail(uiState.value.cocktail.toCocktail())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class CocktailDetailsUiState(
    val cocktail: CocktailDetails = CocktailDetails()
)