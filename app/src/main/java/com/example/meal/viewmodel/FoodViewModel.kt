package com.example.meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meal.data.model.Food
import com.example.meal.data.model.FoodCategory
import com.example.meal.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _randomLiveData = MutableLiveData<Food>()
    val randomLiveData : LiveData<Food> = _randomLiveData

    private val _categoriesLiveData = MutableLiveData<List<FoodCategory>>()
    val categoriesLiveData : LiveData<List<FoodCategory>> = _categoriesLiveData

    private val _searchLiveData = MutableLiveData<List<Food>>()
    val searchLiveData : LiveData<List<Food>> = _searchLiveData

    init {
        getRandomFood()
        getCategories()
    }

    fun insert(food: Food){
        viewModelScope.launch {
            repository.upsert(food)
        }
    }

    fun delete(food: Food){
        viewModelScope.launch {
            repository.deleteFood(food)
        }
    }

    fun getAllFood() : LiveData<List<Food>>{
        return repository.getAllFood()
    }

    fun searchFood(searchFood : String) = viewModelScope.launch {
        repository.searchFood(searchFood).let { response ->
            if (response.isSuccessful){
                val search = response.body()!!.meals
                _searchLiveData.value = search
            }
        }
    }

    private fun getCategories() = viewModelScope.launch {
        repository.getCategories().let { response ->
            if(response.isSuccessful){
                val categories = response.body()!!.categories
                _categoriesLiveData.value = categories
            }
        }
    }

    private fun getRandomFood() = viewModelScope.launch {
        repository.getRandomFood().let { response ->
            if (response.isSuccessful){
                val randomFood : Food = response.body()!!.meals[0]
                _randomLiveData.value = randomFood
            }
        }
    }
}