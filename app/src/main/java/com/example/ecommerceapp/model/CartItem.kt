package com.example.ecommerceapp.model

data class CartItem(
    val productId: String,
    val productName: String,
    val price: String,
    val quantity: Int = 1
)

