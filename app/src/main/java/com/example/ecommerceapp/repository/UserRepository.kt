package com.example.ecommerceapp.repository

import com.example.ecommerceapp.model.LoginRequest
import com.example.ecommerceapp.model.LoginResponse
import com.example.ecommerceapp.model.RegisterRequest
import com.example.ecommerceapp.model.RegisterResponse
import com.example.ecommerceapp.remote.ApiClient
import retrofit2.Call

class UserRepository {

    fun loginUser(request: LoginRequest): Call<LoginResponse> {
        return ApiClient.instance.loginUser(request)
    }

    fun registerUser(request: RegisterRequest): Call<RegisterResponse> {
        return ApiClient.instance.registerUser(request)
    }

}
