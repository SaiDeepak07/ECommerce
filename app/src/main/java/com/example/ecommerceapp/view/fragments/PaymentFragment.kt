package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ecommerceapp.databinding.FragmentPaymentBinding
import com.example.ecommerceapp.model.Order
import com.example.ecommerceapp.model.OrderStorage
import com.example.ecommerceapp.utils.CartStorage
import java.util.UUID

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnPlaceOrder.setOnClickListener {
            val cardNumber = binding.etCardNumber.text.toString().trim()
            val expiry = binding.etExpiry.text.toString().trim()
            val cvv = binding.etCvv.text.toString().trim()
            val cartItems = CartStorage.loadCart(requireContext())
            val totalAmount = cartItems.sumOf { it.price.toDouble() * it.quantity }

            val newOrder = Order(
                orderId = UUID.randomUUID().toString(),
                items = cartItems,
                totalAmount = totalAmount,
                timestamp = System.currentTimeMillis()
            )

            OrderStorage.saveOrder(requireContext(), newOrder)
            CartStorage.saveCart(requireContext(), mutableListOf()) // clear cart


            if (cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(requireContext(), "Enter all card details", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Order Placed Successfully!", Toast.LENGTH_LONG).show()
            }
            val activity = requireActivity() as AppCompatActivity
            activity.setSupportActionBar(binding.toolbar)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.title = "Your Fragment Title"
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}