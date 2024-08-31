package com.example.employeeschedulertk.presentation.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentProfileBinding
import com.example.employeeschedulertk.presentation.EmployeeApplication
import com.example.employeeschedulertk.presentation.ViewModelFactory
import com.example.employeeschedulertk.presentation.login.LoginFragment
import com.example.employeeschedulertk.presentation.supervisor.SupervisorProfileFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.position.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.navFragment,SupervisorProfileFragment())
                    .commit()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar.visibility=View.VISIBLE
                binding.cardViewPerson.visibility=View.INVISIBLE

            }
            else{
                binding.progressBar.visibility=View.INVISIBLE
                binding.cardViewPerson.visibility=View.VISIBLE
                loadData()

            }

        }
        binding.tvName.setOnClickListener {
            viewModel.signOut()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navFragment,LoginFragment())
                .commit()
        }

    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.getDataUser().collect {
                with(binding) {
                    with(this@ProfileFragment) {
                        tvAddress.text = getString(R.string.address, it.address)
                        tvName.text = getString(R.string.name, it.firstName)
                        tvLastName.text = getString(R.string.lastName, it.lastName)
                        tvSurName.text = getString(R.string.surName, it.surName)
                        tvPosition.text = getString(R.string.position, it.position)
                        tvExperience.text =
                            getString(R.string.experience, it.experience.toString())
                        val test=it.schedule.map {

                        }
                        tvSchedule.text=getString(R.string.schedule,it.schedule)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}