package com.example.foodapp.converter

import androidx.room.TypeConverter
import com.example.foodapp.models.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryListConverter(category:List<CategoryItems>):String?{
        if (category==null) {
            return (null)
        }else
        {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.toJson(category,type)
        }
    }
    @TypeConverter
    fun toCategoryListConverter(categoryString: String):List<CategoryItems>?{
        if (categoryString==null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.fromJson(categoryString,type)
        }
    }
}