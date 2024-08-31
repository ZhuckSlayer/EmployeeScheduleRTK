package com.example.employeeschedulertk.presentation.agents

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentAgentsBinding
import com.example.employeeschedulertk.presentation.EmployeeApplication
import com.example.employeeschedulertk.presentation.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AgentsFragment : Fragment() {

    private var _binding: FragmentAgentsBinding? = null
    private val binding:FragmentAgentsBinding
        get() = _binding!!

    private lateinit var viewModel: AgentsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EmployeeApplication).component
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[AgentsViewModel::class.java]
        return binding.root
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AgentsAdapter()
        binding.rvAgents.adapter = adapter
        lifecycleScope.launch {
            viewModel.employeeInfo.collect{
                adapter.submitList(it.toList())
            }
        }
    }
}