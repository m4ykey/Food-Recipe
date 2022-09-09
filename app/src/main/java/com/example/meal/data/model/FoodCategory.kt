package com.example.meal.data.model

data class FoodCategory(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
)

data class FoodCategoryList(
    val categories: List<FoodCategory>,
)
