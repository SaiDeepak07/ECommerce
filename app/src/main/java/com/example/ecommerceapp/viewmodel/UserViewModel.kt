package com.example.ecommerceapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        loadUserFromPrefs()
    }

    private fun loadUserFromPrefs() {
        val prefs = getApplication<Application>().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val id = prefs.getString("userId", null)
        val name = prefs.getString("fullName", null)
        val email = prefs.getString("emailId", null)
        val mobile = prefs.getString("mobileNo", null)

        if (id != null && name != null && email != null && mobile != null) {
            _user.value = User(userId = id, fullName = name, emailId = email, mobileNo = mobile)
        } else {
            _user.value = null
        }

    }

    fun clearUser() {
        val prefs = getApplication<Application>().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
        _user.value = null
    }
}
