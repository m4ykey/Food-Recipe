package com.example.meal.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
) : Parcelable

data class CategoryList(
    val categories: List<Category>,
)
