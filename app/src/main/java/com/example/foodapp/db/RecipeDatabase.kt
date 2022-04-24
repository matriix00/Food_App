package com.example.foodapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.converter.CategoryListConverter
import com.example.foodapp.converter.MealListConverter
import com.example.foodapp.models.*

@Database(entities = [Recipe::class, Category::class, CategoryItems::class, Meal::class, MealsItems::class], version = 1, exportSchema = false)
@TypeConverters(CategoryListConverter::class,MealListConverter::class)
abstract class RecipeDatabase :RoomDatabase() {
    companion object{
        var recipeDatabase:RecipeDatabase?=null
        @Synchronized
        fun getDatabase(context:Context):RecipeDatabase{
            if (recipeDatabase==null){
                recipeDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe_db"
                ).build()
            }
            return recipeDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}