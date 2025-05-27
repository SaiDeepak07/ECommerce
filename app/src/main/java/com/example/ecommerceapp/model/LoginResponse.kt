package com.example.ecommerceapp.model
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val status: Int,
    val message: String,
    val user: User?
)





