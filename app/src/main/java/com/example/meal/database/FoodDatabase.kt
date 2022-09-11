package com.example.meal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meal.data.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao() : FoodDao

    companion object{
        @Volatile
        var INSTANCE : FoodDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : FoodDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context, FoodDatabase::class.java,
                    "meal_db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }
}