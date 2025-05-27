package com.example.ecommerceapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemOrderBinding
import com.example.ecommerceapp.model.Order
import java.text.SimpleDateFormat
import java.util.*

class OrdersAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

        with(holder.binding) {
            tvOrderId.text = "Order #${order.orderId.takeLast(6)}"
            tvDate.text = sdf.format(Date(order.timestamp))
            tvAmount.text = "Total: $%.2f".format(order.totalAmount)
            tvItems.text = order.items.joinToString(", ") { it.productName }
        }
    }

    override fun getItemCount(): Int = orders.size
}
