package com.example.ecommerceapp.model

data class Order(
    val orderId: String,
    val items: List<Product>,
    val totalAmount: Double,
    val timestamp: Long
)
