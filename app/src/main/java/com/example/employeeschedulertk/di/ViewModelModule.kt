package com.example.employeeschedulertk.di

import androidx.lifecycle.ViewModel
import com.example.employeeschedulertk.presentation.LoginViewModel
import com.example.employeeschedulertk.presentation.ProfileViewModel
import dagger.Binds
import dagger.MapKey
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
}