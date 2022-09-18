package com.example.meal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meal.R
import com.example.meal.adapter.SearchFavAdapter
import com.example.meal.databinding.FragmentSearchBinding
import com.example.meal.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: FoodViewModel by viewModels()
    private lateinit var searchAdapter: SearchFavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.searchFood("a")
        viewModel.searchLiveData.observe(viewLifecycleOwner) { search ->
            searchAdapter.differ.submitList(search)
        }
        searchRecyclerView()

        var job: Job? = null
        binding.etSearchFood.addTextChangedListener { searchQuery ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(200)
                viewModel.searchFood(searchQuery.toString())
            }
        }

        searchAdapter.setOnItemClick {
            val bundle = Bundle().apply {
                putParcelable("food", it)
            }
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }

    private fun searchRecyclerView() {
        searchAdapter = SearchFavAdapter()
        binding.rvSearch.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }
}