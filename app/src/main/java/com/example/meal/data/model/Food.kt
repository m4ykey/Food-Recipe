package com.example.meal.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey
    val idMeal : String,
    val strMeal : String,
    val strCategory : String,
    val strArea : String,
    val strInstructions : String,
    val strMealThumb : String,
) : Parcelable

data class FoodList(
    val meals : List<Food>
)
