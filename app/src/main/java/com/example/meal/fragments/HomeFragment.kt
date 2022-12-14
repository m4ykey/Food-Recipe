package com.example.meal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.meal.R
import com.example.meal.adapter.CategoryHomeAdapter
import com.example.meal.data.model.Food
import com.example.meal.databinding.FragmentHomeBinding
import com.example.meal.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: FoodViewModel by viewModels()
    private lateinit var randomFood: Food
    private lateinit var categoryHomeAdapter: CategoryHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.randomLiveData.observe(viewLifecycleOwner) { food ->
            Glide.with(requireContext())
                .load(food.strMealThumb)
                .centerCrop()
                .into(binding.imgFoodHome)
            binding.txtFoodName.text = food.strMeal
            this.randomFood = food
        }

        binding.imgFav.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }

        categoryRvSetup()
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { categories ->
            categoryHomeAdapter.differ.submitList(categories)
        }

        binding.etSearch.apply {
            isFocusable = false
            setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
    }

    private fun categoryRvSetup() {
        categoryHomeAdapter = CategoryHomeAdapter()
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryHomeAdapter
        }
    }
}