package com.tolganacar.foodorderapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tolganacar.foodorderapp.data.entity.Foods
import com.tolganacar.foodorderapp.databinding.FoodRecyclerRowBinding

class FoodsAdapter(
    var mContext: Context,
    var foodList: List<Foods>
) : RecyclerView.Adapter<FoodsAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(var binding: FoodRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = FoodRecyclerRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList.get(position)
        val t = holder.binding

        t.textViewFoodName.text = food.yemek_adi
        resimGoster(food.yemek_resim_adi, t.imageViewFood)
        t.textViewPrice.text = food.yemek_fiyat.toString()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun resimGoster(imageName: String, imageView: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Glide.with(mContext.applicationContext).load(url).into(imageView)
    }
}