package com.example.employeeschedulertk.presentation

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.FragmentProfileBinding
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private var _binding:FragmentProfileBinding?=null
    private val binding:FragmentProfileBinding
        get() = _binding!!

    lateinit var viewModel: ProfileViewModel

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
        _binding= FragmentProfileBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this,viewModelFactory)[ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataUser().observe(viewLifecycleOwner){
                with(binding){
                    with(this@ProfileFragment){
                        tvAddress.text = getString(R.string.address, it.address)
                        tvName.text = getString(R.string.name, it.firstName)
                        tvLastName.text = getString(R.string.lastName, it.lastName)
                        tvSurName.text = getString(R.string.surName, it.surName)
                        tvAge.text = getString(R.string.age, it.age.toString())
                        tvPosition.text = getString(R.string.position, it.position)
                        tvPhoneNumber.text = getString(R.string.phoneNumber, it.phoneNumber)
                        tvExperience.text = getString(R.string.experience, it.experience.toString())
                        tvSalary.text = getString(R.string.salary, it.salary.toString())
                        tvConnectedServices.text = getString(
                            R.string.connectedServices,
                            it.numberOfConnectedServices.toString()
                        )
                        tvAwaitingServices.text =
                            getString(R.string.awaitingServices, it.numberOfAwaitingServices.toString())
                        tvConnectedInternet.text = getString(
                            R.string.connectedInternet,
                            it.numberConnectedInternet.toString()
                        )
                        Log.d("Testi",it.numberConnectedTv.toString())
                        tvConnectedSimCard.text =
                            getString(R.string.connectedSimCard, it.numberConnectedSimCard.toString())
                        tvConnectedTv.text =
                            getString(R.string.connectedTV, it.numberConnectedTv.toString())
                    }
                }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}