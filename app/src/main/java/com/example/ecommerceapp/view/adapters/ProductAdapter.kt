package com.example.ecommerceapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ItemProductBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.utils.CartStorage
import com.example.ecommerceapp.viewmodel.CartViewModel

class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val cartViewModel: CartViewModel
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        val b = holder.binding

        b.tvName.text = product.productName
        b.tvPrice.text = "$${product.price}"
        b.ivProduct.setImageResource(product.productImageRes)

        b.tvAddToCart.setOnClickListener {
            val cart = CartStorage.loadCart(context)
            val existing = cart.find { it.productId == product.productId }

            if (existing != null) {
                existing.quantity += 1
            } else {
                cart.add(
                    Product(
                        productId = product.productId,
                        productName = product.productName,
                        description = product.description,
                        price = product.price,
                        productImageRes = product.productImageRes,
                        quantity = 1
                    )
                )
            }

            CartStorage.saveCart(context, cart)
            Toast.makeText(context, "${product.productName} added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = productList.size
}
