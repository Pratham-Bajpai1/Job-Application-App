package com.example.jobapplicationapp

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }
}