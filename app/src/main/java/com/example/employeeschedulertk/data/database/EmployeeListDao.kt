package com.example.employeeschedulertk.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmployeeListDao {

    @Query("SELECT * FROM Employee_Info WHERE id=:id")
    fun getEmployee(id:String):LiveData<EmployeeDbModel>

    @Query("SELECT * FROM Employee_Info")
    fun getEmployeeList():LiveData<List<EmployeeDbModel>>
}