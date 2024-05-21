package com.example.employeeschedulertk.domain

import javax.inject.Inject

class GetEmployeeListUseCase @Inject constructor(private val employeeRepository: EmployeeRepository) {
    operator fun invoke()=employeeRepository.getEmployeeList()
}