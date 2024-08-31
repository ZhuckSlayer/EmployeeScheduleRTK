package com.example.employeeschedulertk.domain

import androidx.lifecycle.LiveData
import com.example.employeeschedulertk.data.database.EmployeeDbModel
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface EmployeeRepository {

    fun getEmployeeList(): StateFlow<List<EmployeeInfo>>
    fun getEmployee(id:String):EmployeeInfo

    fun getEmployeeFlow(id:String):Flow<EmployeeInfo>


    suspend fun insertEmployee(snapshot: DataSnapshot)
    fun updateEmployeeSchedule(auth:String,children:String)

}