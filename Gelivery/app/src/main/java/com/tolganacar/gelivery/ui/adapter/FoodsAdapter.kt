package com.tolganacar.gelivery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tolganacar.gelivery.databinding.FoodRecyclerRowBinding
import com.tolganacar.gelivery.data.entity.Foods
import com.tolganacar.gelivery.ui.fragments.FoodListFragmentDirections
import com.tolganacar.gelivery.utils.nav

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
        showFoodImage(food.yemek_resim_adi, t.imageViewFood)
        t.textViewPrice.text = food.yemek_fiyat.toString()

        t.cardViewFood.setOnClickListener {
            val gecis = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(food = food)
            Navigation.nav(it,gecis)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    private fun showFoodImage(imageName: String, imageView: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Glide.with(mContext.applicationContext).load(url).override(490, 490).into(imageView)
    }
}