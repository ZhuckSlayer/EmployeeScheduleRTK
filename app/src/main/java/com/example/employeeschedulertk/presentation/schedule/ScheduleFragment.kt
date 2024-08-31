package com.example.employeeschedulertk.presentation.schedule

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentScheduleBinding
import com.example.employeeschedulertk.presentation.EmployeeApplication
import com.example.employeeschedulertk.presentation.ViewModelFactory
import javax.inject.Inject

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding?=null
    private val binding:FragmentScheduleBinding
        get() = _binding!!

    private lateinit var viewModel: ScheduleViewModel

    @Inject
    lateinit var viewModelFactory:ViewModelFactory


    private val component by lazy {
        (requireActivity().application as EmployeeApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentScheduleBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this,viewModelFactory)[ScheduleViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}