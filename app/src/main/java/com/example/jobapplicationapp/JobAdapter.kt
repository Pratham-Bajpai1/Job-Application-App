package com.example.jobapplicationapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private val onEditClick: (Job) -> Unit) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private var jobList = emptyList<Job>()

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobTitle: TextView = itemView.findViewById(R.id.JobTitle)
        val organizationName: TextView = itemView.findViewById(R.id.OrganizationName)
        val jobStatus: TextView = itemView.findViewById(R.id.JobStatus)
        val editButton: Button = itemView.findViewById(R.id.btn_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_item, parent, false)
        return JobViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val currentJob = jobList[position]
        Log.d("JobAdapter", "Binding job: ${currentJob.jobTitle}, ${currentJob.organizationName}")
        holder.jobTitle.text = currentJob.jobTitle
        holder.organizationName.text = currentJob.organizationName
        holder.jobStatus.text = currentJob.status

        // Set click listener for edit button
        holder.editButton.setOnClickListener {
            onEditClick(currentJob)
        }
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    fun setJobs(jobs: List<Job>) {
        Log.d("JobAdapter", "Job list size: ${jobs.size}")
        this.jobList = jobs
        notifyDataSetChanged()
    }
}