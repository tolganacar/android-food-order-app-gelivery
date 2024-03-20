package com.tolganacar.gelivery.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.nav(it: View, id:Int){
    findNavController(it).navigate(id)
}

fun Navigation.nav(it: View, id: NavDirections){
    findNavController(it).navigate(id)
}