package com.example.cocktails.data

import com.example.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: CocktailsRepository {
    private val list = FakeDataSource.cocktailList.toMutableList()

    override fun getAllCocktailsStream(): Flow<List<Cocktail>> {
        return flow { emit(list.toList()) }
    }

    override fun getCocktailStream(id: Int): Flow<Cocktail?> {
        return flow { emit(list[id]) }
    }

    override suspend fun insertCocktail(cocktail: Cocktail) {
        list.add(cocktail)
    }

    override suspend fun deleteCocktail(cocktail: Cocktail) {
        list.remove(cocktail)
    }

    override suspend fun updateCocktail(cocktail: Cocktail) {
        list[cocktail.id] = cocktail
    }
}