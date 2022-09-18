package com.example.meal.data.repository

import androidx.lifecycle.LiveData
import com.example.meal.api.FoodApi
import com.example.meal.data.model.Food
import com.example.meal.data.model.FoodList
import com.example.meal.database.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api : FoodApi,
    private val dao : FoodDao
){

    suspend fun getRandomFood() = api.getRandomFood()
    suspend fun getCategories() = api.getCategories()
    suspend fun searchFood(search : String) = api.searchFood(search)
    suspend fun categoryFoodId(category : String) = api.categoryFoodId(category)

    suspend fun deleteFood(food: Food) = dao.deleteFood(food)
    suspend fun upsert(food: Food) = dao.upsert(food)
    fun getAllFood() : LiveData<List<Food>> = dao.getAllFood()
}