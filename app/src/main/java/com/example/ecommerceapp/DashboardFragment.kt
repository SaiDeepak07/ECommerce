package com.example.ecommerceapp

import SmartphonesFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        val prefs = requireContext().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val name = prefs.getString("fullName", "Guest")
        val email = prefs.getString("emailId", "guest@example.com")
        val mobile = prefs.getString("mobileNo", "N/A")

        val headerView = binding.navigationView.getHeaderView(0)
        val nameView = headerView.findViewById<TextView>(R.id.tvUserName)
        val emailView = headerView.findViewById<TextView>(R.id.tvUserEmail)
        val mobileView = headerView.findViewById<TextView>(R.id.tvUserMobile)

        nameView.text = "Welcome $name"
        emailView.text = email
        mobileView.text = mobile

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        drawerToggle = ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(requireContext(), "Home clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> Toast.makeText(requireContext(), "Logging out", Toast.LENGTH_SHORT).show()
            }
            binding.drawerLayout.closeDrawers()
            true
        }

        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategories.adapter = CategoryAdapter(requireContext(), sampleCategories()) { category ->
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

    override fun onDestroyView() {
        super.onDestroyView()

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

