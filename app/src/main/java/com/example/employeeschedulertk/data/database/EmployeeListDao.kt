package com.example.employeeschedulertk.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeListDao {

    @Query("SELECT * FROM Employee_Info WHERE id=:id")
    fun getEmployee(id: String):EmployeeDbModel

    @Query("SELECT * FROM Employee_Info WHERE id=:id")
    fun getEmployeeFlow(id: String):Flow<EmployeeDbModel>

    @Query("SELECT * FROM Employee_Info")
    suspend fun getEmployeeList(): List<EmployeeDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employeeDbModel: EmployeeDbModel)
}