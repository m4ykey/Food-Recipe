package com.example.meal.api

import com.example.meal.data.model.FoodCategoryList
import com.example.meal.data.model.FoodList
import com.example.meal.util.Constants.CATEGORIES
import com.example.meal.util.Constants.RANDOM
import com.example.meal.util.Constants.SEARCH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET(RANDOM)
    suspend fun getRandomFood() : Response<FoodList>

    @GET(CATEGORIES)
    suspend fun getCategories() : Response<FoodCategoryList>

    @GET(SEARCH)
    suspend fun searchFood(
        @Query("s") searchFood : String
    ) : Response<FoodList>
}