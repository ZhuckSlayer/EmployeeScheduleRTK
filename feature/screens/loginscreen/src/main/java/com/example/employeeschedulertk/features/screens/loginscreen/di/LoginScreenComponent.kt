package com.example.employeeschedulertk.features.screens.loginscreen.di

import android.util.Log
import dagger.Subcomponent

@Subcomponent
class LoginScreenComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():LoginScreenComponent
    }

    fun inject(fragment:)

}

interface LoginScreenProvider{
    fun provideLoginScreenComponent():LoginScreenComponent
}