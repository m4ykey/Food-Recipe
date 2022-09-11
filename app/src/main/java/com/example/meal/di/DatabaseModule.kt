package com.example.meal.di

import android.app.Application
import com.example.meal.database.FoodDao
import com.example.meal.database.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : FoodDatabase{
        return FoodDatabase.getDatabase(application)
    }

    @Provides
    @Singleton
    fun provideDao(foodDatabase: FoodDatabase) : FoodDao {
        return foodDatabase.foodDao()
    }
}