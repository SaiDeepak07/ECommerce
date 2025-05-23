package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentCartBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.view.adapters.CartAdapter

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dummyCartItems = listOf(
            Product("1", "OnePlus 9 Pro", "16GB RAM, 256GB", "745", R.drawable.oneplus_9_pro),
            Product("2", "Samsung S21 Plus", "12GB RAM, 128GB", "950", R.drawable.samsung_s21_plus)
        )

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = CartAdapter(dummyCartItems)
    }
}
