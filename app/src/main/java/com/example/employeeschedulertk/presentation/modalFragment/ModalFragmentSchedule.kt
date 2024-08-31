package com.example.employeeschedulertk.presentation.modalFragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentModalFragmentScheduleBinding
import com.example.employeeschedulertk.presentation.EmployeeApplication
import com.example.employeeschedulertk.presentation.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModalFragmentSchedule : BottomSheetDialogFragment() {

    companion object {
        const val CURRENT_DAY = "current_day"
        fun newInstance(cityName: Int): BottomSheetDialogFragment =
            ModalFragmentSchedule().apply {
                arguments = Bundle().apply {
                    putInt(CURRENT_DAY, cityName)
                }
            }
    }


    private var _binding: FragmentModalFragmentScheduleBinding? = null
    private val binding
        get() = _binding!!


    private val component by lazy {
        (requireActivity().application as EmployeeApplication).component
    }

    lateinit var viewModel: ModalFragmentScheduleViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModalFragmentScheduleBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[ModalFragmentScheduleViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ScheduleAdapter()
        binding.rvSchedule.adapter = adapter
        val currentDay = requireArguments().getInt(CURRENT_DAY)
        viewModel.getScheduleOnDay(currentDay)

        lifecycleScope.launch {
            viewModel.testList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}