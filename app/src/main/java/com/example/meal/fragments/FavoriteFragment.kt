package com.example.meal.fragments

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meal.R
import com.example.meal.adapter.SearchFavAdapter
import com.example.meal.databinding.FragmentFavoriteBinding
import com.example.meal.viewmodel.FoodViewModel
import com.google.android.material.snackbar.Snackbar
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
            if (favorite.isEmpty()){
                binding.emptyContainer.root.visibility = View.VISIBLE
                return@observe
            }
            favAdapter.differ.submitList(favorite)
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        favAdapter.setOnItemClick {
            val bundle = Bundle().apply {
                putParcelable("food", it)
            }
            findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deleteItem = favAdapter.differ.currentList[position]
                viewModel.delete(deleteItem)

                Snackbar.make(view, "Removed from favorite", Snackbar.LENGTH_LONG).show()
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorite)
    }

    private fun setupRecyclerView() {
        favAdapter = SearchFavAdapter()
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = favAdapter
        }
    }
}