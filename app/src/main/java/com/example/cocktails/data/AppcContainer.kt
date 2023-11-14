package com.example.cocktails.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val cocktailsRepository: CocktailsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineCocktailsRepository]
 */
class AppDataContainer(private val context: Context): AppContainer {
    /**
     * Implementation for [CocktailsRepository]
     */
    override val cocktailsRepository: CocktailsRepository by lazy {
        OfflineCocktailsRepository(
            AppDatabase.getDatabase(context).cocktailDao()
        )
    }
}