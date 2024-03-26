package com.tolganacar.gelivery.ui.viewmodel.mapper

import com.tolganacar.gelivery.data.entity.CartFoods
import com.tolganacar.gelivery.ui.viewmodel.model.CartUI
import javax.inject.Inject

class FoodsRepositoryMapper @Inject constructor() {
    fun map(cartFoods: List<CartFoods>): List<CartUI> {
        val resultMap = mutableMapOf<String, MutableList<Int>>()

        for (cart in cartFoods) {
            if (resultMap.containsKey(cart.yemek_adi)) {
                resultMap[cart.yemek_adi]?.add(cart.sepet_yemek_id)
            } else {
                resultMap[cart.yemek_adi] = mutableListOf(cart.sepet_yemek_id)
            }
        }

        return resultMap.map { (yemekAdi, idList) ->
            CartUI(
                sepet_yemek_id_list = idList,
                yemek_adi = yemekAdi,
                yemek_resim_adi = cartFoods.find { it.yemek_adi == yemekAdi }?.yemek_resim_adi ?: "",
                yemek_fiyat = cartFoods.find { it.yemek_adi == yemekAdi }?.yemek_fiyat ?: 0,
                yemek_siparis_adet = cartFoods.filter { it.yemek_adi == yemekAdi }.sumOf { it.yemek_siparis_adet },
                kullanici_adi = cartFoods.find { it.yemek_adi == yemekAdi }?.kullanici_adi ?: ""
            )
        }
    }
}