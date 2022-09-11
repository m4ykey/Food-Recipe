package com.example.meal.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.meal.data.model.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(food : Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("SELECT * FROM food_table")
    fun getAllFood() : LiveData<List<Food>>
}