package com.example.cocktails.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktails.model.Cocktail
import com.example.cocktails.model.Converters

@Database(
    entities = [Cocktail::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "cocktail_database")
                    .fallbackToDestructiveMigration()
                    .addTypeConverter(Converters())
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}