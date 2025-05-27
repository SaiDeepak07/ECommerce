package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentCartSummaryBinding
import com.example.ecommerceapp.utils.CartStorage
import com.example.ecommerceapp.view.adapters.CartSummaryAdapter

class CartSummaryFragment : Fragment() {

    private lateinit var binding: FragmentCartSummaryBinding
    private lateinit var adapter: CartSummaryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCartSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cartItems = CartStorage.loadCart(requireContext())
        adapter = CartSummaryAdapter(cartItems)
        binding.rvOrderSummary.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderSummary.adapter = adapter

        val total = cartItems.sumOf { it.price.toDouble() * it.quantity }
        binding.tvTotalAmount.text = "$%.2f".format(total)

        binding.btnNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, AddressFragment())
                .addToBackStack(null)
                .commit()
        }

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = "Cart Summary"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
