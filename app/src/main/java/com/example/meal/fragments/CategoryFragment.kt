package com.example.meal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meal.R
import com.example.meal.adapter.FoodCategoryAdapter
import com.example.meal.data.model.Food
import com.example.meal.databinding.FragmentCategoryBinding
import com.example.meal.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding
    private val viewModel : FoodViewModel by viewModels()
    private lateinit var foodCategoryAdapter: FoodCategoryAdapter
    private val args : CategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val food = args.food

        viewModel.categoryFoodId(food.idMeal)
        viewModel.categoryLiveData.observe(viewLifecycleOwner) { foodCategory ->
            foodCategoryAdapter.differ.submitList(foodCategory)
        }
        setupRecyclerView()

        onItemClick()
    }

    private fun onItemClick() {
        foodCategoryAdapter.setOnItemClick {
            val bundle = Bundle().apply {
                putParcelable("food", it)
            }
            findNavController().navigate(R.id.action_categoryFragment_to_detailFragment, bundle)
        }
    }

    private fun setupRecyclerView() {
        foodCategoryAdapter = FoodCategoryAdapter()
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = foodCategoryAdapter
        }
    }
}