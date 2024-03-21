package com.tolganacar.gelivery.data.datasource

import com.tolganacar.gelivery.data.entity.Foods
import com.tolganacar.gelivery.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource(var foodsDao: FoodsDao) {

    suspend fun loadFoods(): List<Foods> = withContext(Dispatchers.IO) {
        return@withContext foodsDao.loadFoods().yemekler
    }

    suspend fun addToCart(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = foodsDao.addToCart(
        yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi
    )
}