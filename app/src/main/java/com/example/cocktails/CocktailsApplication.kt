package com.example.cocktails

import android.app.Application
import com.example.cocktails.data.AppContainer
import com.example.cocktails.data.AppDataContainer

class CocktailsApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}