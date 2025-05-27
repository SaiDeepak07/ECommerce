package com.example.ecommerceapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapp.databinding.FragmentRegisterBinding
import com.example.ecommerceapp.model.RegisterRequest
import com.example.ecommerceapp.viewmodel.AppViewModelFactory
import com.example.ecommerceapp.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = AppViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        viewModel.registerResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack() // Navigate back to login
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val mobile = binding.etMobile.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (name.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val request = RegisterRequest(name, mobile, email, password)
                viewModel.registerUser(request)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvAlreadyUser.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
