package com.example.meal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.meal.R
import com.example.meal.databinding.FragmentDetailBinding
import com.example.meal.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel : FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val food = args.food

        Glide.with(requireContext())
            .load(food.strMealThumb)
            .centerCrop()
            .into(binding.imgFoodDetail)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.imgFav.setOnClickListener {
            viewModel.insert(food)
            Toast.makeText(requireContext(), "${food.strMeal} added to favorite", Toast.LENGTH_SHORT).show()
        }

        binding.txtCategory.text = food.strCategory
        binding.txtLocation.text = food.strArea
        binding.txtFood.text = food.strMeal
        binding.txtDescription.text = food.strInstructions

    }
}