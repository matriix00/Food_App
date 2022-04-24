package com.example.foodapp.converter

import androidx.room.TypeConverter
import com.example.foodapp.models.CategoryItems
import com.example.foodapp.models.MealsItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {
    @TypeConverter
    fun fromCategoryListConverter(meal:List<MealsItems>):String?{
        if (meal==null) {
            return (null)
        }else
        {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.toJson(meal,type)
        }
    }
    @TypeConverter
    fun toCategoryListConverter(mealString: String):List<MealsItems>?{
        if (mealString==null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.fromJson(mealString,type)
        }
    }
}