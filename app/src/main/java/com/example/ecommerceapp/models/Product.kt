package com.example.ecommerceapp.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id") val productId: String,
    @SerializedName("product_name") val productName: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("product_image_url") val productImageUrl: String
)

