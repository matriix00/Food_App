package com.example.foodapp.models

import androidx.room.*
import com.example.foodapp.converter.CategoryListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id :Int,

    @ColumnInfo(name = "categoryitems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryListConverter::class)
    var categoryitems:List<CategoryItems>?=null
)

