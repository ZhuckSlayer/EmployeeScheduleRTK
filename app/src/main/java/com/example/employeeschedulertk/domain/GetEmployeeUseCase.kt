package com.example.employeeschedulertk.domain

import javax.inject.Inject

class GetEmployeeUseCase @Inject constructor(private val employeeRepository: EmployeeRepository) {
    operator fun invoke(id:String)=employeeRepository.getEmployee(id)
}