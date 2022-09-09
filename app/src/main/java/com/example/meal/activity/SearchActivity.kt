package com.example.meal.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meal.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}