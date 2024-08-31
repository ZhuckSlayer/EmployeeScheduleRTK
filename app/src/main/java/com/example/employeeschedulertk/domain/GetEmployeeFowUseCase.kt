package com.example.employeeschedulertk.domain

import javax.inject.Inject

data class GetEmployeeFowUseCase @Inject constructor(private val employeeRepository: EmployeeRepository) {
    operator fun invoke(id:String)=employeeRepository.getEmployeeFlow(id)
}
