package com.example.foodapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    @ColumnInfo(name = "dish_name")
    var dishName:String
):Serializable