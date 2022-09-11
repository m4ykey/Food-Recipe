package com.example.meal.data.repository

import com.example.meal.api.FoodApi
import com.example.meal.data.model.FoodList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api : FoodApi
){

    suspend fun getRandomFood() = api.getRandomFood()
    suspend fun getCategories() = api.getCategories()
    suspend fun searchFood(search : String) = api.searchFood(search)
}