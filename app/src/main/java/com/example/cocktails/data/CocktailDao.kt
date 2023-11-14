package com.example.cocktails.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
    @Insert
    suspend fun insert(cocktail: Cocktail)

    @Update
    suspend fun update(cocktail: Cocktail)

    @Delete
    suspend fun delete(cocktail: Cocktail)

    @Query("SELECT * FROM cocktails WHERE id = :id")
    fun getCocktail(id: Int): Flow<Cocktail>

    @Query("SELECT * FROM cocktails ORDER BY name ASC")
    fun getAllCocktails(): Flow<List<Cocktail>>
}