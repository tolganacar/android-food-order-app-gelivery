package com.tolganacar.gelivery.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tolganacar.gelivery.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(var foodsRepository: FoodsRepository): ViewModel() {

    fun addToCart(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }
}