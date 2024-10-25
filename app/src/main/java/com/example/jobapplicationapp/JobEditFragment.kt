package com.example.jobapplicationapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class JobEditFragment : Fragment() {

    private lateinit var jobViewModel: JobViewModel
    private lateinit var statusSpinner: Spinner
    private lateinit var currentJob: Job // The job being edited

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_edit, container, false)

        jobViewModel = ViewModelProvider(this).get(JobViewModel::class.java)

        statusSpinner = view.findViewById(R.id.edit_spinner_status)
        setupStatusSpinner()

        val jobTitle = view.findViewById<EditText>(R.id.edit_job_title)
        val organizationName = view.findViewById<EditText>(R.id.edit_organization_name)
        val jobCategory = view.findViewById<EditText>(R.id.edit_job_category)
        val skillsRequired = view.findViewById<EditText>(R.id.edit_skills_required)
        val btnDeleteJob = view.findViewById<Button>(R.id.btn_delete_job)

        // Retrieve the job data passed to this fragment (ensure you're passing this from the job list)
        currentJob = arguments?.getParcelable("job") ?: Job(0, "", "", "", "", "", "")

        // Pre-fill the form with existing job details
        jobTitle.setText(currentJob.jobTitle)
        organizationName.setText(currentJob.organizationName)
        jobCategory.setText(currentJob.jobCategory)
        skillsRequired.setText(currentJob.skillsRequired)

        // Set the spinner selection based on current job status
        val statusOptions = resources.getStringArray(R.array.status_options)
        val statusIndex = statusOptions.indexOf(currentJob.status)
        statusSpinner.setSelection(statusIndex)

        val btnUpdateJob = view.findViewById<Button>(R.id.btn_update_job)
        btnUpdateJob.setOnClickListener {
            updateJobInDatabase(jobTitle.text.toString(), organizationName.text.toString(),
                jobCategory.text.toString(), skillsRequired.text.toString(), statusSpinner.selectedItem.toString())
        }

        btnDeleteJob.setOnClickListener {
            deleteJobFromDatabase()
        }

        return view
    }

    private fun setupStatusSpinner() {
        val statusOptions = arrayOf("Applied", "Interview", "Offer", "Rejected")
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, statusOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner.adapter = adapter
    }

    private fun updateJobInDatabase(
        jobTitle: String, organizationName: String, jobCategory: String,
        skillsRequired: String, status: String
    ) {
        if (jobTitle.isNotEmpty() && organizationName.isNotEmpty()) {
            val updatedJob = currentJob.copy(
                jobTitle = jobTitle,
                organizationName = organizationName,
                jobCategory = jobCategory,
                skillsRequired = skillsRequired,
                status = status
            )

            jobViewModel.updateJob(updatedJob)
            Toast.makeText(activity, "Job Updated Successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Please fill all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteJobFromDatabase() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Job")
            .setMessage("Are you sure you want to delete this job?")
            .setPositiveButton("Yes") { _, _ ->
                jobViewModel.deleteJob(currentJob)
                Toast.makeText(activity, "Job Deleted", Toast.LENGTH_SHORT).show()

                // Navigate back to the job list after deletion
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, JobListFragment())
                    .addToBackStack(null)
                    .commit()
            }
            .setNegativeButton("No", null)
            .show()
    }

}