package com.example.cocktails.data

import com.example.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow

interface CocktailsRepository {
    fun getAllCocktailsStream(): Flow<List<Cocktail>>

    fun getCocktailStream(id: Int): Flow<Cocktail?>

    suspend fun insertCocktail(cocktail: Cocktail)

    suspend fun deleteCocktail(cocktail: Cocktail)

    suspend fun updateCocktail(cocktail: Cocktail)
}