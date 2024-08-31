package com.example.employeeschedulertk.presentation.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.example.employeeschedulertk.domain.GetEmployeeListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AgentsViewModel @Inject constructor(
    private val getEmployeeListUseCase: GetEmployeeListUseCase
) : ViewModel() {


    val employeeInfo=getEmployeeListUseCase()
}