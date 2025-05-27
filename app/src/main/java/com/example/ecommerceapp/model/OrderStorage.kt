package com.example.ecommerceapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrderStorage {
    private const val PREF_NAME = "orders_pref"
    private const val KEY_ORDERS = "order_list"

    fun saveOrder(context: Context, order: Order) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val existingJson = prefs.getString(KEY_ORDERS, null)
        val orderList = if (existingJson != null) {
            val type = object : TypeToken<MutableList<Order>>() {}.type
            Gson().fromJson<MutableList<Order>>(existingJson, type)
        } else mutableListOf()

        orderList.add(0, order) // most recent first

        prefs.edit().putString(KEY_ORDERS, Gson().toJson(orderList)).apply()
    }

    fun loadOrders(context: Context): List<Order> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_ORDERS, null)
        return if (json != null) {
            val type = object : TypeToken<List<Order>>() {}.type
            Gson().fromJson(json, type)
        } else emptyList()
    }
}
