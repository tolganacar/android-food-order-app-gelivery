package com.tolganacar.gelivery.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tolganacar.gelivery.R
import com.tolganacar.gelivery.databinding.FragmentCartBinding
import com.tolganacar.gelivery.ui.adapter.CartAdapter
import com.tolganacar.gelivery.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        observeFoodCartList()
        observeTotalPrice()
        buttonConfirmCartOnClick()

        viewModel.getCartFoods("nacar")

        return binding.root
    }

    private fun observeFoodCartList() {
        viewModel.foodCartList.observe(viewLifecycleOwner) {
            val cartAdapter = CartAdapter(requireContext(), it, viewModel)
            binding.recyclerViewCart.adapter = cartAdapter
            binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeTotalPrice() {
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.textViewSubtotalPrice.text = it.toString()
            if (binding.textViewSubtotalPrice.text.equals("0")) {
                binding.textViewTotalPriceCart.text = "0"
            } else {
                binding.textViewTotalPriceCart.text = (it + 10).toString()
            }
        }
    }

    private fun buttonConfirmCartOnClick() {
        binding.buttonConfirmCart.setOnClickListener {
            if (!viewModel.foodCartList.value.isNullOrEmpty()) {
                Snackbar.make(it, resources.getText(R.string.confirm), Snackbar.LENGTH_LONG)
                    .setAction(resources.getText(R.string.yes)) {
                        viewModel.deleteAllFoods("nacar")
                        Snackbar.make(it, resources.getText(R.string.confirmed), Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.YELLOW)
                            .setTextColor(Color.BLACK)
                            .show()
                    }
                    .setBackgroundTint(Color.YELLOW)
                    .setTextColor(Color.BLACK)
                    .setActionTextColor(Color.RED)
                    .show()
            } else {
                Snackbar.make(
                    requireView(),
                    R.string.add_items,
                    Snackbar.LENGTH_SHORT
                )
                    .setTextColor(Color.RED)
                    .setBackgroundTint(Color.WHITE)
                    .show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartFoods("nacar")
    }
}