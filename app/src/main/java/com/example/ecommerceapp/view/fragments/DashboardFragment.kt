package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.Category
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentDashboardBinding
import com.example.ecommerceapp.view.adapters.CategoryAdapter
import com.example.ecommerceapp.viewmodel.UserViewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[UserViewModel::class.java]

        // Observe and update user info in navigation header
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            val header = binding.navigationView.getHeaderView(0)
            header.findViewById<TextView>(R.id.tvUserName).text = "Welcome ${user?.fullName ?: "Guest"}"
            header.findViewById<TextView>(R.id.tvUserEmail).text = user?.emailId ?: "-"
            header.findViewById<TextView>(R.id.tvUserMobile).text = user?.mobileNo ?: "-"
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        // Set up navigation drawer
        drawerToggle = ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        // Handle drawer item clicks
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(requireContext(), "Home clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cart -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, CartFragment())
                        .addToBackStack(null)
                        .commit()
                }
                R.id.nav_orders -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, OrdersFragment())
                        .addToBackStack(null)
                        .commit()
                }
                R.id.nav_logout -> {
                    userViewModel.clearUser()
                    Toast.makeText(requireContext(), "Logging out...", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, LoginFragment())
                        .commit()
                }
            }
            binding.drawerLayout.closeDrawers()
            true
        }

        // Setup category list
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategories.adapter = CategoryAdapter(sampleCategories()) { category ->
        if (category.title == "Smart Phones") {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, SmartphonesFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Clicked: ${category.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dashboard, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sampleCategories() = listOf(
        Category("Smart Phones", R.drawable.smartphones),
        Category("Laptops", R.drawable.laptops),
        Category("Mens Wear", R.drawable.mens_wear),
        Category("Women’s Wear", R.drawable.womens_wear),
        Category("Kids Wear", R.drawable.kida_wear),
        Category("Grocery", R.drawable.groceries)
    )
}
