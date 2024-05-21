package com.example.employeeschedulertk.domain

import androidx.lifecycle.LiveData

interface EmployeeRepository {

    fun getEmployeeList():LiveData<List<EmployeeInfo>>
    fun getEmployee(id:String):LiveData<EmployeeInfo>

}