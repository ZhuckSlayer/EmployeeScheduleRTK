package com.example.employeeschedulertk.di

import android.app.Application
import com.example.employeeschedulertk.presentation.login.LoginFragment
import com.example.employeeschedulertk.presentation.MainActivity
import com.example.employeeschedulertk.presentation.agents.AgentsFragment
import com.example.employeeschedulertk.presentation.profile.ProfileFragment
import com.example.employeeschedulertk.presentation.schedule.ScheduleFragment
import com.example.employeeschedulertk.presentation.supervisor.SupervisorProfileFragment
import com.example.employeeschedulertk.presentation.weekends.ChooseDaysFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class,DataModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: SupervisorProfileFragment)

    fun inject(fragment: AgentsFragment)

    fun inject(fragment: ChooseDaysFragment)

    fun inject(fragment:ScheduleFragment)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}