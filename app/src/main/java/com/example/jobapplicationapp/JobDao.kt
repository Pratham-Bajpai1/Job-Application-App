package com.example.jobapplicationapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Update
    suspend fun updateJob(job: Job)

    @Delete
    suspend fun delete(job: Job)

    @Query("SELECT * FROM job_table WHERE status = :status ORDER BY jobId ASC")
    fun getJobsByStatus(status: String): LiveData<List<Job>>

    @Query("SELECT * FROM job_table")
    fun getAllJobs(): LiveData<List<Job>>
}