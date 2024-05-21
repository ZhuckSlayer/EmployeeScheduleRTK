package com.example.employeeschedulertk.domain

import com.google.firebase.database.DataSnapshot
import javax.inject.Inject

class InsertEmployeeUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) {
    suspend operator fun invoke(snapshot: DataSnapshot)=employeeRepository.insertEmployee(snapshot)
}