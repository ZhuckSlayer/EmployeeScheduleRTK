package com.example.employeeschedulertk.di

import android.app.Activity
import android.app.Application
import com.example.employeeschedulertk.presentation.LoginFragment
import com.example.employeeschedulertk.presentation.MainActivity
import com.example.employeeschedulertk.presentation.ProfileFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class,DataModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment:LoginFragment)

    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}