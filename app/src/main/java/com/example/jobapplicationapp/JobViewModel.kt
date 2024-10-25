package com.example.jobapplicationapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobRepository
    val allJobs: LiveData<List<Job>>

    init {
        val jobDao = EmployeeDatabase.getDatabase(application).jobDao()
        repository = JobRepository(jobDao)
        allJobs = repository.getAllJobs()
    }

    fun getJobsByStatus(status: String): LiveData<List<Job>> {
        return if (status == "All") {
            allJobs
        } else {
            repository.getJobsByStatus(status)
        }
    }

    fun insertJob(job: Job) = viewModelScope.launch {
        repository.insertJob(job)
    }

    fun updateJob(job: Job) = viewModelScope.launch {
        repository.updateJob(job)
    }

    fun deleteJob(job: Job) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(job)
        }
    }
}