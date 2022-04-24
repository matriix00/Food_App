package com.example.retrofit
import com.example.foodapp.models.Category
import com.example.foodapp.models.Meal
import com.example.foodapp.models.MealResponse
import com.example.foodapp.models.MealsItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataServiceInterface {
    @GET("categories.php")
    fun getCategoriesList():Call<Category>
    @GET("filter.php")
    fun getMealsList(@Query("c")category: String):Call<Meal>
    @GET("lookup.php")
    fun getSpecificMeal(@Query("i")id: String):Call<MealResponse>

}