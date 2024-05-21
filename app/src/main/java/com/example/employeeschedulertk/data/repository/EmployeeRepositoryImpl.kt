package com.example.employeeschedulertk.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.employeeschedulertk.data.mapper.EmployeeMapper
import com.example.employeeschedulertk.data.database.EmployeeListDao
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.example.employeeschedulertk.domain.EmployeeRepository
import com.google.firebase.database.DataSnapshot
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val mapper: EmployeeMapper,
    private val employeeListDao: EmployeeListDao
):EmployeeRepository {
    override fun getEmployeeList(): LiveData<List<EmployeeInfo>> {
        return employeeListDao.getEmployeeList().map {
            it.map {its->
                mapper.mapDbModelToEntity(its)
            }
        }
    }

    override fun getEmployee(id: String): LiveData<EmployeeInfo> {
        return employeeListDao.getEmployee(id).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun insertEmployee(snapshot: DataSnapshot) {
        employeeListDao.insertEmployee(mapper.mapDtoToDb(snapshot))
    }
}