package com.tolganacar.gelivery.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.gelivery.data.entity.Foods
import com.tolganacar.gelivery.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()

    init {
        loadFoods()
    }

    fun loadFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodsRepository.loadFoods()
        }
    }

    fun searchFoods(searchQuery: String) {
        val filteredList = foodList.value?.filter { food ->
            food.yemek_adi.contains(searchQuery, ignoreCase = true)
        }
        foodList.value = filteredList
    }
}