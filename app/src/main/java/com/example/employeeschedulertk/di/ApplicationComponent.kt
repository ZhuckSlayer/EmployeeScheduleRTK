package com.example.employeeschedulertk.di

import android.app.Activity
import android.app.Application
import com.example.employeeschedulertk.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class,DataModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}