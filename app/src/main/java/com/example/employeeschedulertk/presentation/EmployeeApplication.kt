package com.example.employeeschedulertk.presentation

import android.app.Application
import com.example.employeeschedulertk.di.DaggerApplicationComponent

class EmployeeApplication:Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}