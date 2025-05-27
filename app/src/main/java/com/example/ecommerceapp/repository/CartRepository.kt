package com.example.ecommerceapp.repository

import com.example.ecommerceapp.model.CartItem

class CartRepository {

    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(item: CartItem) {
        val existing = cartItems.find { it.productId == item.productId }
        if (existing != null) {
            val updated = existing.copy(quantity = existing.quantity + 1)
            cartItems[cartItems.indexOf(existing)] = updated
        } else {
            cartItems.add(item)
        }
    }

    fun getCartItems(): List<CartItem> = cartItems
    fun clearCart() = cartItems.clear()
}
