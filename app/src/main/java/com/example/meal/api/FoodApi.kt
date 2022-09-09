package com.example.meal.api

import com.example.meal.data.model.FoodCategoryList
import com.example.meal.data.model.FoodList
import com.example.meal.util.Constants.CATEGORIES
import com.example.meal.util.Constants.RANDOM
import retrofit2.Response
import retrofit2.http.GET

interface FoodApi {

    @GET(RANDOM)
    suspend fun getRandomFood() : Response<FoodList>

    @GET(CATEGORIES)
    suspend fun getCategories() : Response<FoodCategoryList>
}