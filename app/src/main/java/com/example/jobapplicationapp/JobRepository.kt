package com.example.jobapplicationapp

import androidx.lifecycle.LiveData

class JobRepository(private val jobDao: JobDao) {

    suspend fun insertJob(job: Job) {
        jobDao.insertJob(job)
    }

    suspend fun updateJob(job: Job) {
        jobDao.updateJob(job)
    }

    suspend fun delete(job: Job) {
        jobDao.delete(job)
    }

    fun getAllJobs(): LiveData<List<Job>> {
        return jobDao.getAllJobs()
    }

    fun getJobsByStatus(status: String): LiveData<List<Job>> {
        return jobDao.getJobsByStatus(status)
    }
}