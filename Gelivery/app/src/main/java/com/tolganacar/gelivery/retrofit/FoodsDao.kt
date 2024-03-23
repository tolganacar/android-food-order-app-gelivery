package com.tolganacar.gelivery.retrofit

import com.tolganacar.gelivery.data.entity.CRUDResponse
import com.tolganacar.gelivery.data.entity.CartResponse
import com.tolganacar.gelivery.data.entity.FoodsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFoods(): FoodsResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(@Field("yemek_adi") yemek_adi: String,
                          @Field("yemek_resim_adi") yemek_resim_adi: String,
                          @Field("yemek_fiyat") yemek_fiyat: Int,
                          @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                          @Field("kullanici_adi") kullanici_adi: String) : CRUDResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getCartFoods(@Field("kullanici_adi") kullanici_adi: String) : CartResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFood(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                           @Field("kullanici_adi") kullanici_adi: String) : CRUDResponse
}