package com.example.cocktails.data

import com.example.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow

class OfflineCocktailsRepository(
    private val cocktailDao: CocktailDao
) : CocktailsRepository {
    override fun getAllCocktailsStream(): Flow<List<Cocktail>> =
        cocktailDao.getAllCocktails()

    override fun getCocktailStream(id: Int): Flow<Cocktail?> =
        cocktailDao.getCocktail(id)

    override suspend fun insertCocktail(cocktail: Cocktail) =
        cocktailDao.insert(cocktail)

    override suspend fun deleteCocktail(cocktail: Cocktail) =
        cocktailDao.delete(cocktail)

    override suspend fun updateCocktail(cocktail: Cocktail) =
        cocktailDao.update(cocktail)
}