package com.example.ecommerceapp.remote

import com.example.ecommerceapp.model.LoginRequest
import com.example.ecommerceapp.model.LoginResponse
import com.example.ecommerceapp.model.ProductResponse
import com.example.ecommerceapp.model.RegisterRequest
import com.example.ecommerceapp.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("User/auth")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("User/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("SubCategory/products/{sub_category_id}")
    fun getProductsBySubCategory(
        @Path("sub_category_id") subCategoryId: Int
    ): Call<ProductResponse>

}

