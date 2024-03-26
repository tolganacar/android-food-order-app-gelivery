package com.tolganacar.gelivery.data.repo

import com.tolganacar.gelivery.data.datasource.FoodsDataSource
import com.tolganacar.gelivery.data.entity.CartFoods
import com.tolganacar.gelivery.data.entity.Foods

class FoodsRepository(var foodsDataSource: FoodsDataSource) {

    suspend fun loadFoods(): List<Foods> = foodsDataSource.loadFoods()

    suspend fun addToCart(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = foodsDataSource.addToCart(
        yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi
    )

    suspend fun getCartFoods(kullanici_adi: String) : List<CartFoods> = foodsDataSource.getCartFoods(kullanici_adi)

    suspend fun deleteFood(sepet_yemek_id: Int, kullanici_adi: String) = foodsDataSource.deleteFood(sepet_yemek_id, kullanici_adi)
}