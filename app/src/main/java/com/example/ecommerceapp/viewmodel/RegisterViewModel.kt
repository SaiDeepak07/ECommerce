package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.RegisterRequest
import com.example.ecommerceapp.model.RegisterResponse
import com.example.ecommerceapp.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> get() = _registerResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun registerUser(request: RegisterRequest) {
        repository.registerUser(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body()?.status == 0) {
                    _registerResponse.postValue(response.body())
                } else {
                    _error.postValue(response.body()?.message ?: "Registration failed")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _error.postValue("Error: ${t.message}")
            }
        })
    }
}
