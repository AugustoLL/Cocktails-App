package com.example.cocktails.ui.cocktail.entry

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cocktails.data.CocktailsRepository
import com.example.cocktails.model.Cocktail

private const val TAG = "COCKTAIL_ENTRY_VM"

/**
 * ViewModel to validate and insert cocktails in the Room database.
 */
class CocktailEntryViewModel(
    private val repository: CocktailsRepository
) : ViewModel() {
    /**
     * Holds current item ui state
     */
    var uiState by mutableStateOf(CocktailUiState())
        private set

    fun updateUiState(cocktailDetails: CocktailDetails) {
        uiState = CocktailUiState(
            cocktailDetails = cocktailDetails,
            isEntryValid = validateInputs()
        )
    }
    /**
     * Function to validate the inputs of the CocktailEntryScreen
     * */
    private fun validateInputs(state: CocktailDetails = uiState.cocktailDetails) : Boolean {
        Log.d(
            TAG,
            "Name: ${state.name}\n" +
                    "Ingredients is not empty: ${state.ingredients.isNotEmpty()} \n" +
                    "Preparation Steps is not empty: ${state.preparation.isNotEmpty()}\n" +
                    "Preparation Time: ${state.preparationTime}\n"
        )
        return with(state) {
            name.isNotBlank() &&
                    preparationTime.isNotBlank() &&
                    preparationTime.toDouble() > 0.0 &&
                    ingredients.isNotEmpty() &&
                    preparation.isNotEmpty() &&
                    listElementsAreValid(ingredients) &&
                    listElementsAreValid(preparation)
//                  && stars.isNotEmpty() && stars.toInt() in 0..5

        }
    }

    fun listElementsAreValid(list: List<String>): Boolean {
        var flag = true
        list.forEach { element ->
            if (element.isEmpty()) {
                Log.d(TAG, "FOUND EMPTY ELEMENT IN LIST")
                flag = false
            }
        }
        return flag
    }

    fun addIngredient(ingredient: String) {
        if (ingredient.isNotEmpty()) {
            uiState.ingredientsFieldCount++
            uiState.cocktailDetails.ingredients.add(ingredient)
        }
    }

    fun addPreparationStep(step: String) {
        if (step.isNotEmpty()) {
            uiState.preparationFieldCount++
            uiState.cocktailDetails.preparation.add(step)
        }
    }

    suspend fun saveCocktail() {
        if (validateInputs()) repository.insertCocktail(uiState.cocktailDetails.toCocktail())
    }
}

data class CocktailUiState(
    val cocktailDetails: CocktailDetails = CocktailDetails(),
    val isEntryValid: Boolean = false,
    var ingredientsFieldCount: Int = -1,
    var preparationFieldCount: Int = -1
)

data class CocktailDetails(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val preparationTime: String = "",
    val ingredients: MutableList<String> = mutableListOf(),
    val preparation: MutableList<String> = mutableListOf(),
    val stars: String = ""
)

/**
 * Extension function to convert [CocktailDetails] to [Cocktail].
 * If the value of [CocktailDetails.preparationTime] is not a valid [Double],
 * then the it will be set to 0.0. Similarly if the value of
 * [CocktailDetails.stars] is not a valid [Int], then the it will be set to 0
 */
fun CocktailDetails.toCocktail(): Cocktail = Cocktail(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    preparationTime = preparationTime.toDoubleOrNull() ?: 1.0,
    ingredients = ingredients,
    preparation = preparation,
    stars = stars.toIntOrNull() ?: 0
)

/**
 * Extension function to convert [Cocktail] to [CocktailUiState]
 */
fun Cocktail.toCocktailUiState(isEntryValid: Boolean = false): CocktailUiState = CocktailUiState(
    cocktailDetails = this.toCocktailDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Cocktail] to [CocktailDetails]
 */
fun Cocktail.toCocktailDetails() : CocktailDetails = CocktailDetails(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    preparationTime = preparationTime.toString(),
    ingredients = ingredients.toMutableList(),
    preparation = preparation.toMutableList(),
    stars = stars.toString()
)