package com.example.meal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.meal.databinding.FragmentDetailBinding
import com.example.meal.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: FoodViewModel by viewModels()

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
        }

        binding.txtCategory.text = food.strCategory
        binding.txtLocation.text = food.strArea
        binding.txtFood.text = food.strMeal
        binding.txtDescription.text = food.strInstructions
        binding.ingredient1.text = "· ${food.strIngredient1} · ${food.strMeasure1}"
        binding.ingredient2.text = "· ${food.strIngredient2} · ${food.strMeasure2}"
        binding.ingredient3.text = "· ${food.strIngredient3} · ${food.strMeasure3}"
        binding.ingredient4.text = "· ${food.strIngredient4} · ${food.strMeasure4}"
        binding.ingredient5.text = "· ${food.strIngredient5} · ${food.strMeasure5}"
        binding.ingredient6.text = "· ${food.strIngredient6} · ${food.strMeasure6}"
        binding.ingredient7.text = "· ${food.strIngredient7} · ${food.strMeasure7}"
        binding.ingredient8.text = "· ${food.strIngredient8} · ${food.strMeasure8}"
        binding.ingredient9.text = "· ${food.strIngredient9} · ${food.strMeasure9}"
        binding.ingredient10.text = "· ${food.strIngredient10} · ${food.strMeasure10}"
        binding.ingredient11.text = "· ${food.strIngredient11} · ${food.strMeasure11}"
        binding.ingredient12.text = "· ${food.strIngredient12} · ${food.strMeasure12}"
        binding.ingredient13.text = "· ${food.strIngredient13} · ${food.strMeasure13}"
        binding.ingredient14.text = "· ${food.strIngredient14} · ${food.strMeasure14}"
        binding.ingredient15.text = "· ${food.strIngredient15} · ${food.strMeasure15}"
        binding.ingredient16.text = "· ${food.strIngredient16} · ${food.strMeasure16}"
        binding.ingredient17.text = "· ${food.strIngredient17} · ${food.strMeasure17}"
        binding.ingredient18.text = "· ${food.strIngredient18} · ${food.strMeasure18}"
        binding.ingredient19.text = "· ${food.strIngredient19} · ${food.strMeasure19}"
        binding.ingredient20.text = "· ${food.strIngredient20} · ${food.strMeasure20}"

    }
}