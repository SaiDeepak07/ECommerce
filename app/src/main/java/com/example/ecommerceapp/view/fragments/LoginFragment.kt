package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentLoginBinding
import com.example.ecommerceapp.model.LoginRequest
import com.example.ecommerceapp.view.LoginActivity
import com.example.ecommerceapp.viewmodel.AppViewModelFactory
import com.example.ecommerceapp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = AppViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            val user = response.user
            Toast.makeText(requireContext(), "Welcome ${user?.fullName}", Toast.LENGTH_SHORT).show()

            val prefs = requireContext().getSharedPreferences("user_data", 0)
            prefs.edit().apply {
                putString("userId", user?.userId)
                putString("fullName", user?.fullName)
                putString("emailId", user?.emailId)
                putString("mobileNo", user?.mobileNo)
                apply()
            }

            (requireActivity() as LoginActivity).hideLogo()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, DashboardFragment())
                .commit()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val request = LoginRequest(email, password)
                viewModel.loginUser(request)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegisterHere.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
