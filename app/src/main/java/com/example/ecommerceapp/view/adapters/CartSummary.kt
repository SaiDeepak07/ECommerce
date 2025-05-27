package com.example.ecommerceapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemOrderSummaryBinding
import com.example.ecommerceapp.model.Product

class CartSummaryAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<CartSummaryAdapter.SummaryViewHolder>() {

    inner class SummaryViewHolder(val binding: ItemOrderSummaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val binding = ItemOrderSummaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SummaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val item = products[position]
        val b = holder.binding

        b.ivProduct.setImageResource(item.productImageRes)
        b.tvName.text = item.productName
        b.tvUnitPrice.text = "$${item.price}"
        b.tvQuantity.text = "Qty: ${item.quantity}"
        val amount = item.price.toDouble() * item.quantity
        b.tvAmount.text = "Amount: $%.2f".format(amount)
    }

    override fun getItemCount() = products.size
}
