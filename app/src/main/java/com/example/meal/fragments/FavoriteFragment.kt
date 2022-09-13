package com.example.meal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meal.R
import com.example.meal.adapter.SearchFavAdapter
import com.example.meal.databinding.FragmentFavoriteBinding
import com.example.meal.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var favAdapter: SearchFavAdapter
    private val viewModel : FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getAllFood().observe(viewLifecycleOwner) { favorite ->
            favAdapter.differ.submitList(favorite)
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setupRecyclerView() {
        favAdapter = SearchFavAdapter()
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = favAdapter
        }
    }
}