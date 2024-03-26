package com.tolganacar.gelivery.ui.viewmodel.model

data class CartUI(
    var sepet_yemek_id_list: MutableList<Int>,
    var yemek_adi: String,
    var yemek_resim_adi: String,
    var yemek_fiyat: Int,
    var yemek_siparis_adet: Int,
    var kullanici_adi: String
)