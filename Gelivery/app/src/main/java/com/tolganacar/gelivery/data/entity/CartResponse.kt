package com.tolganacar.gelivery.data.entity

data class CartResponse(var sepet_yemekler: List<CartFoods>,
                        var success: Int)