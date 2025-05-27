package com.example.ecommerceapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemCartProductBinding
import com.example.ecommerceapp.model.Product

class CartAdapter(
    private val productList: MutableList<Product>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = productList[position]
        val b = holder.binding

        b.imgProduct.setImageResource(product.productImageRes)
        b.tvName.text = product.productName
        b.tvDesc.text = product.description
        b.tvPrice.text = "$${product.price}"
        b.tvQuantity.text = product.quantity.toString()

        b.btnIncrease.setOnClickListener {
            product.quantity++
            b.tvQuantity.text = product.quantity.toString()
            onCartChanged()
        }

        b.btnDecrease.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
                b.tvQuantity.text = product.quantity.toString()
            } else {
                productList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, productList.size)
            }
            onCartChanged()
        }
    }

    override fun getItemCount() = productList.size
}
