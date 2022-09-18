package com.example.meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meal.data.model.Category
import com.example.meal.data.model.Food
import com.example.meal.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository,
) : ViewModel() {

    private val _randomLiveData = MutableLiveData<Food>()
    val randomLiveData: LiveData<Food> = _randomLiveData

    private val _categoriesLiveData = MutableLiveData<List<Category>>()
    val categoriesLiveData: LiveData<List<Category>> = _categoriesLiveData

    private val _searchLiveData = MutableLiveData<List<Food>>()
    val searchLiveData: LiveData<List<Food>> = _searchLiveData

    private val _categoryLiveData = MutableLiveData<List<Food>>()
    val categoryLiveData: LiveData<List<Food>> = _categoryLiveData

    init {
        getRandomFood()
        getCategories()
    }

    fun categoryFoodId(category: String) = viewModelScope.launch {
        repository.categoryFoodId(category).let { response ->
            if (response.isSuccessful) {
                val categories = response.body()!!.meals
                _categoryLiveData.value = categories
            }
        }
    }

    fun insert(food: Food) {
        viewModelScope.launch {
            repository.upsert(food)
        }
    }

    fun delete(food: Food) {
        viewModelScope.launch {
            repository.deleteFood(food)
        }
    }

    fun getAllFood(): LiveData<List<Food>> {
        return repository.getAllFood()
    }

    fun searchFood(searchFood: String) = viewModelScope.launch {
        repository.searchFood(searchFood).let { response ->
            if (response.isSuccessful) {
                _searchLiveData.value = response.body()!!.meals
            }
        }
    }

    private fun getCategories() = viewModelScope.launch {
        repository.getCategories().let { response ->
            if (response.isSuccessful) {
                _categoriesLiveData.value = response.body()!!.categories
            }
        }
    }

    private fun getRandomFood() = viewModelScope.launch {
        repository.getRandomFood().let { response ->
            if (response.isSuccessful) {
                val randomFood: Food = response.body()!!.meals[0]
                _randomLiveData.value = randomFood
            }
        }
    }
}