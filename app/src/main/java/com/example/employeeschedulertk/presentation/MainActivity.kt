package com.example.employeeschedulertk.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.data.database.AppDataBase
import com.example.employeeschedulertk.databinding.ActivityMainBinding
import com.example.employeeschedulertk.presentation.agents.AgentsFragment
import com.example.employeeschedulertk.presentation.profile.ProfileFragment
import com.example.employeeschedulertk.presentation.schedule.ScheduleFragment
import com.example.employeeschedulertk.presentation.supervisor.SupervisorProfileFragment
import com.example.employeeschedulertk.presentation.weekends.ChooseDaysFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as EmployeeApplication).component
    }

    lateinit var viewModel: MainViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.navFragment) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.user.observe(this) {
            if (it != null) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.INVISIBLE
            }
        }



        viewModel.position.observe(this) {
            binding.bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.profileFragment -> {
                        if (it) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.navFragment, ProfileFragment())
                                .commit()

                        } else {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.navFragment, SupervisorProfileFragment())
                                .commit()
                        }
                        true
                    }

                    R.id.chooseDaysFragment -> {
                        if (it) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.navFragment, ChooseDaysFragment())
                                .commit()
                        } else {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.navFragment, ScheduleFragment())
                                .commit()
                        }
                        true
                    }

                    R.id.agentsFragment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.navFragment, AgentsFragment())
                            .commit()
                        true
                    }

                    else -> false
                }
            }
        }


    }


}