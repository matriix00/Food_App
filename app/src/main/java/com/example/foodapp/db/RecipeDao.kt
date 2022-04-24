package com.example.foodapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.models.Category
import com.example.foodapp.models.CategoryItems
import com.example.foodapp.models.MealsItems
import com.example.foodapp.models.Recipe
@Dao
interface RecipeDao {
    @Query("select * from categoryitems order by id desc")
    suspend fun getAllCategory () :  List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(categoryItems: CategoryItems?)

    @Query("delete from categoryitems")
    suspend fun clearCatdb()

    @Query("select * from mealitems where categoryName = :categoryName order by id desc")
    suspend fun getSpecificMeal (categoryName:String) :  List<MealsItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeal(mealsItems: MealsItems?)

   /* @Query("delete from mealitems")
    suspend fun clearMealdb()*/
}