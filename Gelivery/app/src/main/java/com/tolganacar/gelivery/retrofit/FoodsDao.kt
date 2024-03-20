package com.tolganacar.gelivery.retrofit

import com.tolganacar.gelivery.data.entity.FoodsResponse
import retrofit2.http.GET

interface FoodsDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFoods(): FoodsResponse
}