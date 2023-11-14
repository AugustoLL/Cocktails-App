package com.example.cocktails.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

data class FakeCocktail(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
    val preparationMinutes: Int = 0,
    val ingredients: List<String>,
    val preparation: List<String>,
    val stars: Int
)

@Entity(tableName = "cocktails")
data class Cocktail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "preparation_time_min")
    val preparationTime: Double,
    @ColumnInfo(name = "stars")
    val stars: Int,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<String>,
    @ColumnInfo(name = "preparation_steps")
    val preparation: List<String>
)

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun ListToString(list: List<String>?) : String {
        return list?.joinToString(":") ?: ""
    }

    @TypeConverter
    fun StringToList(string: String?) : List<String> {
        return string?.splitToSequence(":")?.toList() ?: listOf()
    }
}