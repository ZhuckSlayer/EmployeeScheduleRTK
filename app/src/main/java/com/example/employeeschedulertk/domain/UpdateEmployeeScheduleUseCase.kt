package com.example.employeeschedulertk.domain

import javax.inject.Inject

class UpdateEmployeeScheduleUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) {
    operator fun invoke(auth: String, children:  String) =
        employeeRepository.updateEmployeeSchedule(auth, children)
}