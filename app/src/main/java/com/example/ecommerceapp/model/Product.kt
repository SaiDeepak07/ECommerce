package com.example.ecommerceapp.model

import com.google.gson.annotations.SerializedName

data class Product(
    val productId: String,
    val productName: String,
    val description: String,
    val price: String,
    val productImageRes: Int,
    var quantity: Int = 1
)



