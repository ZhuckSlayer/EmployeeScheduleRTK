package com.example.employeeschedulertk.data.repository

import com.example.employeeschedulertk.data.mapper.EmployeeMapper
import com.example.employeeschedulertk.data.database.EmployeeListDao
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.example.employeeschedulertk.domain.EmployeeRepository
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val mapper: EmployeeMapper,
    private val employeeListDao: EmployeeListDao,
    private val fireBaseDataHelper: FireBaseDataHelper
) : EmployeeRepository {


    private val scope = CoroutineScope(Dispatchers.Main)

    private val _employeeInfo= mutableListOf<EmployeeInfo>()
    private val employeeInfo:List<EmployeeInfo>
        get() = _employeeInfo.toList()


    private val _employeeListInfo = mutableListOf<EmployeeInfo>()
    private val employeeListInfo: List<EmployeeInfo>
        get() = _employeeListInfo.toList()

    private val employeeListInfoFlow = flow {
        val employeeListInfos = employeeListDao.getEmployeeList().map {
            mapper.mapDbModelToEntity(it)
        }
        _employeeListInfo.addAll(employeeListInfos)
        emit(employeeListInfos)
        _employeeListInfo.clear()
    }



    override fun getEmployeeList(): StateFlow<List<EmployeeInfo>> = employeeListInfoFlow.stateIn(
        scope = scope,
        started = SharingStarted.Lazily,
        initialValue = employeeListInfo
    )

    override fun getEmployee(id: String): EmployeeInfo {
        return mapper.mapDbModelToEntity(employeeListDao.getEmployee(id))
    }

    override fun getEmployeeFlow(id: String): Flow<EmployeeInfo> {
        return employeeListDao.getEmployeeFlow(id).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun insertEmployee(snapshot: DataSnapshot) {
        employeeListDao.insertEmployee(mapper.mapDtoToDb(snapshot))
    }

    override fun updateEmployeeSchedule(auth:String,children:String) {
        val child= mutableMapOf("Days" to children)
        fireBaseDataHelper.dataBaseReference.child("Employee").child(auth)
            .updateChildren(child as Map<String, Any>)
    }
}