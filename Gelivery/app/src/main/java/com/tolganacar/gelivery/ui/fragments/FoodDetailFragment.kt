package com.tolganacar.gelivery.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.tolganacar.gelivery.databinding.FragmentFoodDetailBinding
import com.tolganacar.gelivery.ui.viewmodel.FoodDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)

        val bundle:FoodDetailFragmentArgs by navArgs()
        val foodDetail = bundle.food
        var quantity = 1
        val foodPrice = foodDetail.yemek_fiyat.toString()
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

        binding.buttonAddToCart.setOnClickListener {
            viewModel.addToCart(foodDetail.yemek_adi, foodDetail.yemek_resim_adi, foodDetail.yemek_fiyat, quantity, "nacar")
            Snackbar.make(it, "${foodDetail.yemek_adi} has been added to the cart.", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun showFoodImage(imageName: String, imageView: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Glide.with(this).load(url).into(imageView)
    }
}