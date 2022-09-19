package com.example.meal.data.model

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
)

data class CategoryList(
    val categories: List<Category>,
)
