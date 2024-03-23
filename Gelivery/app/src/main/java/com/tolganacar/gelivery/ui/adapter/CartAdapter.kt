package com.tolganacar.gelivery.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.tolganacar.gelivery.data.entity.CartFoods
import com.tolganacar.gelivery.databinding.CartRecyclerRowBinding
import com.tolganacar.gelivery.ui.viewmodel.CartViewModel

class CartAdapter(
    var mContext: Context,
    var foodCartList: List<CartFoods>,
    var viewModel: CartViewModel
) : RecyclerView.Adapter<CartAdapter.FoodCartViewHolder>() {

    inner class FoodCartViewHolder(var binding: CartRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCartViewHolder {
        val binding = CartRecyclerRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FoodCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCartViewHolder, position: Int) {
        val foodCart = foodCartList.get(position)
        val t = holder.binding
        val foodTotalPrice = (foodCart.yemek_siparis_adet * foodCart.yemek_fiyat).toString()

        t.textViewFoodNameCart.text = foodCart.yemek_adi
        showFoodImage(foodCart.yemek_resim_adi, t.imageViewCartFood)
        t.textViewPriceCart.text = foodCart.yemek_fiyat.toString()
        t.textViewFoodQuantityCart.text = foodCart.yemek_siparis_adet.toString()
        t.textViewFoodTotalPriceCart.text = foodTotalPrice

        t.imageViewDeleteFoodCart.setOnClickListener {
            Snackbar.make(it,"Are you sure you want to delete ${foodCart.yemek_adi}?", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.YELLOW)
                .setAction("YES"){
                    viewModel.deleteFood(foodCart.sepet_yemek_id, "nacar")
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return foodCartList.size
    }

    private fun showFoodImage(imageName: String, imageView: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Glide.with(mContext.applicationContext).load(url).override(490, 490).into(imageView)
    }
}