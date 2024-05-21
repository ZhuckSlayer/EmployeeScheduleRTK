package com.example.employeeschedulertk.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentLoginBinding
import javax.inject.Inject


class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding?=null
    private val binding:FragmentLoginBinding
        get() = _binding!!

    private val component by lazy {
        (requireActivity().application as EmployeeApplication).component
    }

    lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this,viewModelFactory)[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textWatchListeners()
        binding.buttonSignIn.setOnClickListener {
            viewModel.signIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
        viewModel.user.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
        }
    }
    private fun textWatchListeners() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}