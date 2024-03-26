package com.tolganacar.gelivery.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tolganacar.gelivery.databinding.FragmentFoodListBinding
import com.tolganacar.gelivery.ui.adapter.FoodsAdapter
import com.tolganacar.gelivery.ui.viewmodel.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodListFragment : Fragment() {
    private lateinit var binding: FragmentFoodListBinding
    private lateinit var viewModel: FoodListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodListBinding.inflate(inflater, container, false)

        viewModel.foodList.observe(viewLifecycleOwner){
            val foodsAdapter = FoodsAdapter(requireContext(),it)
            binding.recyclerViewFood.adapter = foodsAdapter
            binding.recyclerViewFood.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.loadFoods()
                } else {
                    viewModel.searchFoods(newText)
                }
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchFoods(query)
                return true
            }
        })


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodListViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
    }
}