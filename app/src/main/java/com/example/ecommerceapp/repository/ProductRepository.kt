package com.example.ecommerceapp.repository

import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Product

class ProductRepository {

    fun getLocalProducts(): List<Product> {
        return listOf(
            Product("1", "OnePlus 9 Pro", "Android 10, 16GB RAM, 256GB Storage", "745", R.drawable.oneplus_9_pro),
            Product("2", "Samsung S21 Plus", "Android 11, 12GB RAM, 128GB Storage", "950", R.drawable.samsung_s21_plus),
            Product("3", "iPhone 13 Pro", "iOS 15, 6GB RAM, 256GB Storage", "1199", R.drawable.iphone_13_pro),
            Product("4", "Google Pixel 6", "Android 12, 8GB RAM, 128GB Storage", "699", R.drawable.pixel_6),
            Product("5", "Xiaomi Mi 11 Ultra", "Android 11, 12GB RAM, 256GB Storage", "899", R.drawable.mi_11_ultra),
            Product("6", "Realme GT Neo 2", "Android 11, 8GB RAM, 128GB Storage", "499", R.drawable.oneplus_9_pro),
            Product("7", "Motorola Edge 30", "Android 12, 8GB RAM, 128GB Storage", "599", R.drawable.samsung_s21_plus),
            Product("8", "Asus ROG Phone 5", "Android 11, 16GB RAM, 512GB Storage", "999", R.drawable.iphone_13_pro),
            Product("9", "Vivo X70 Pro", "Android 11, 12GB RAM, 256GB Storage", "789", R.drawable.oneplus_9_pro),
            Product("10", "Oppo Reno 6 Pro", "Android 11, 8GB RAM, 128GB Storage", "549", R.drawable.mi_11_ultra),
            Product("11", "Nokia X20", "Android 11, 6GB RAM, 128GB Storage", "429", R.drawable.samsung_s21_plus),
            Product("12", "Infinix Zero 5G", "Android 11, 8GB RAM, 128GB Storage", "299", R.drawable.samsung_s21_plus)
        )
    }
}
