package com.example.ecommerceapp.utils

import android.content.Context
import com.example.ecommerceapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartStorage {
    private const val PREF_NAME = "cart_pref"
    private const val KEY_CART = "cart_items"

    fun saveCart(context: Context, cart: List<Product>) {
        val json = Gson().toJson(cart)
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY_CART, json).apply()
    }

    fun loadCart(context: Context): MutableList<Product> {
        val json = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_CART, null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Product>>() {}.type
            Gson().fromJson(json, type)
        } else mutableListOf()
    }
}
