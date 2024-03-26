package com.tolganacar.gelivery.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.gelivery.data.repo.FoodsRepository
import com.tolganacar.gelivery.ui.viewmodel.mapper.FoodsRepositoryMapper
import com.tolganacar.gelivery.ui.viewmodel.model.CartUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    var foodsRepository: FoodsRepository,
    private val mapper: FoodsRepositoryMapper
) : ViewModel() {
    var foodCartList = MutableLiveData<List<CartUI>>()
    var totalPrice = MutableLiveData<Int>()

    init {
        getCartFoods("nacar")
    }

    fun deleteAllFoods(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodCartList.value?.forEach { food ->
                food.sepet_yemek_id_list.forEach {
                    foodsRepository.deleteFood(it, kullanici_adi)
                }
            }
            foodCartList.value = emptyList()
            calculateTotalPrice()
        }
    }

    fun deleteAllFoodByName(name: String, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodCartList.value?.forEach {
                if (it.yemek_adi == name) {
                    it.sepet_yemek_id_list.forEach { id ->
                        foodsRepository.deleteFood(id, kullanici_adi)
                    }
                }
            }
            getCartFoods("nacar")
        }
    }

    fun getCartFoods(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                foodCartList.value = mapper.map(foodsRepository.getCartFoods(kullanici_adi))
                calculateTotalPrice()
            } catch (e: Exception) {
                e.printStackTrace()
                foodCartList.value = listOf()
                calculateTotalPrice()
            }
        }
    }

    private fun calculateTotalPrice() {
        val total = foodCartList.value?.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
        totalPrice.value = total ?: 0
    }
}