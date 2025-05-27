package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSmartphonesBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.view.adapters.ProductAdapter
import com.example.ecommerceapp.viewmodel.AppViewModelFactory
import com.example.ecommerceapp.viewmodel.CartViewModel

class SmartphonesFragment : Fragment() {

    private var _binding: FragmentSmartphonesBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmartphonesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = AppViewModelFactory()
        cartViewModel = ViewModelProvider(requireActivity(), factory)[CartViewModel::class.java]

        val localSmartphones = listOf(
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

        binding.rvPhones.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPhones.adapter = ProductAdapter(requireContext(), localSmartphones, cartViewModel)

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = "Your Fragment Title"

        setHasOptionsMenu(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
