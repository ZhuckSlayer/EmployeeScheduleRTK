package com.example.employeeschedulertk.di

import androidx.lifecycle.ViewModel
import com.example.employeeschedulertk.presentation.login.LoginViewModel
import com.example.employeeschedulertk.presentation.MainViewModel
import com.example.employeeschedulertk.presentation.agents.AgentsViewModel
import com.example.employeeschedulertk.presentation.modalFragment.ModalFragmentScheduleViewModel
import com.example.employeeschedulertk.presentation.profile.ProfileViewModel
import com.example.employeeschedulertk.presentation.supervisor.SupervisorProfileViewModel
import com.example.employeeschedulertk.presentation.weekends.ChooseDaysViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(LoginViewModel::class)
    fun bindsLoginViewModel(viewModel: LoginViewModel):ViewModel
    @IntoMap
    @Binds
    @ViewModelKey(ProfileViewModel::class)
    fun bindsProfileViewModel(viewModel: ProfileViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SupervisorProfileViewModel::class)
    fun bindsSupervisorProfileViewModel(viewModel: SupervisorProfileViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AgentsViewModel::class)
    fun bindsAgentsViewModel(viewModel: AgentsViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ChooseDaysViewModel::class)
    fun bindsChooseDaysViewModel(viewModel: ChooseDaysViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ModalFragmentScheduleViewModel::class)
    fun bindsModalFragmentViewModel(viewModel: ModalFragmentScheduleViewModel):ViewModel
}