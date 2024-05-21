package com.example.employeeschedulertk.di

import android.app.Application
import com.example.employeeschedulertk.data.database.AppDataBase
import com.example.employeeschedulertk.data.database.EmployeeListDao
import com.example.employeeschedulertk.data.repository.EmployeeRepositoryImpl
import com.example.employeeschedulertk.domain.EmployeeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindEmployeeRepositoryImpl(impl: EmployeeRepositoryImpl):EmployeeRepository

    companion object{
        @Provides
        @ApplicationScope
        fun provideEmployeeListDao(application: Application):EmployeeListDao{
            return AppDataBase.getInstance(application).employeeListDao()
        }
    }
}