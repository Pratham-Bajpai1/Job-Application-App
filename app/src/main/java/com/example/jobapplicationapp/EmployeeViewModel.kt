package com.example.jobapplicationapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmployeeRepository

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
    }

    fun registerEmployee(employee: Employee) = viewModelScope.launch {
        repository.insert(employee)
    }
}