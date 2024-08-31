package com.example.employeeschedulertk.presentation.supervisor

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentSupervisorProfileBinding
import com.example.employeeschedulertk.presentation.EmployeeApplication
import com.example.employeeschedulertk.presentation.ViewModelFactory
import com.example.employeeschedulertk.presentation.profile.ProfileViewModel
import javax.inject.Inject


class SupervisorProfileFragment : Fragment() {


    private var _binding:FragmentSupervisorProfileBinding?=null
    private val binding:FragmentSupervisorProfileBinding
        get() = _binding!!

    private lateinit var viewModel: SupervisorProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EmployeeApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentSupervisorProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

    }
}