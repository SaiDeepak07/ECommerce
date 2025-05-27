package com.example.ecommerceapp.viewmodel

import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.CartItem
import com.example.ecommerceapp.repository.CartRepository

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> get() = _cartItems


    fun addToCart(item: CartItem) {
        repository.addToCart(item)
        _cartItems.value = repository.getCartItems()
    }

    fun getCartItems() {
        _cartItems.value = repository.getCartItems()
    }
}
