package com.tolganacar.gelivery.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.gelivery.data.entity.CartFoods
import com.tolganacar.gelivery.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {
    var foodCartList = MutableLiveData<List<CartFoods>>()
    var totalPrice = MutableLiveData<Int>()

    init {
        getCartFoods("nacar")
    }

    fun deleteAllFoods(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodCartList.value?.forEach { food ->
                foodsRepository.deleteFood(food.sepet_yemek_id, kullanici_adi)
            }
            foodCartList.value = emptyList()
            calculateTotalPrice()
        }
    }

    fun deleteFood(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            if (foodCartList.value?.size == 1) {
                foodsRepository.deleteFood(sepet_yemek_id, kullanici_adi)
                foodCartList.value = emptyList()
                calculateTotalPrice()
            } else {
                foodsRepository.deleteFood(sepet_yemek_id, kullanici_adi)
                calculateTotalPrice()
            }
            getCartFoods("nacar")
        }
    }

    fun getCartFoods(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                foodCartList.value = foodsRepository.getCartFoods(kullanici_adi)
                calculateTotalPrice()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun calculateTotalPrice() {
        val total = foodCartList.value?.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
        totalPrice.value = total ?: 0
    }
}