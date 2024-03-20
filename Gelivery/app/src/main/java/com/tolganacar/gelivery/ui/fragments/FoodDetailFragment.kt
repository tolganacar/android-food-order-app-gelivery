package com.tolganacar.gelivery.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tolganacar.gelivery.R
import com.tolganacar.gelivery.databinding.FragmentFoodDetailBinding

class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)

        val bundle:FoodDetailFragmentArgs by navArgs()
        val foodDetail = bundle.food
        var quantity = 1
        var foodPrice = foodDetail.yemek_fiyat.toString()
        var totalPrice = foodDetail.yemek_fiyat.toString()

        binding.textViewFoodNameDetail.text = foodDetail.yemek_adi
        binding.textViewFoodPriceDetail.text = foodPrice
        showFoodImage(foodDetail.yemek_resim_adi, binding.imageViewFoodDetail)
        binding.textViewTotalPrice.text = totalPrice

        binding.buttonIncreaseQuantity.setOnClickListener {
            quantity++
            totalPrice = (foodPrice.toInt() * quantity).toString()
            binding.textViewQuantity.text = quantity.toString()
            binding.textViewTotalPrice.text = totalPrice
        }

        binding.buttonDecreaseQuantity.setOnClickListener {
            if (quantity > 1) {
                quantity--
                totalPrice = (foodPrice.toInt() * quantity).toString()
                binding.textViewQuantity.text = quantity.toString()
                binding.textViewTotalPrice.text = totalPrice
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showFoodImage(imageName: String, imageView: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Glide.with(this).load(url).into(imageView)
    }
}