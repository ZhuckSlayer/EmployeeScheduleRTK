package com.example.employeeschedulertk.domain

import androidx.lifecycle.LiveData
import com.example.employeeschedulertk.data.database.EmployeeDbModel
import com.google.firebase.database.DataSnapshot

interface EmployeeRepository {

    fun getEmployeeList():LiveData<List<EmployeeInfo>>
    fun getEmployee(id:String):LiveData<EmployeeInfo>

    suspend fun insertEmployee(snapshot: DataSnapshot)

}