package com.example.jobapplicationapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JobListFragment : Fragment() {

    private lateinit var jobViewModel: JobViewModel
    private lateinit var jobAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_list, container, false)

        jobViewModel = ViewModelProvider(this).get(JobViewModel::class.java)

        val jobRecyclerView = view.findViewById<RecyclerView>(R.id.jobRecyclerView)
        jobAdapter = JobAdapter { job ->
            // Pass the selected job to JobEditFragment for editing
            navigateToEditFragment(job)
        }

        jobRecyclerView.adapter = jobAdapter
        jobRecyclerView.layoutManager = LinearLayoutManager(activity)

        // Observe changes in the job list
        jobViewModel.allJobs.observe(viewLifecycleOwner, { jobs ->
            if (jobs != null && jobs.isNotEmpty()) {
                jobAdapter.setJobs(jobs)
            } else {
                Log.d("JobListFragment", "No jobs found")
                // Optionally, you can display a placeholder message when no jobs are found
            }
        })

        // Setup filtering
        setupFilter(view)

        return view
    }

    private fun setupFilter(view: View) {
        val statusFilterSpinner = view.findViewById<Spinner>(R.id.statusFilterSpinner)
        val statusOptions = arrayOf("All", "Applied", "Interview", "Offer", "Rejected")
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, statusOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusFilterSpinner.adapter = adapter

        statusFilterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedStatus = statusOptions[position]
                jobViewModel.getJobsByStatus(selectedStatus).observe(viewLifecycleOwner, { jobs ->
                    jobAdapter.setJobs(jobs)
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Default behavior: show all jobs
                jobViewModel.allJobs.observe(viewLifecycleOwner, { jobs ->
                    jobAdapter.setJobs(jobs)
                })
            }
        }
    }

    // Navigate to JobEditFragment when a job is clicked
    private fun navigateToEditFragment(job: Job) {
        val bundle = Bundle().apply {
            putParcelable("job", job) // Pass the clicked job
        }

        val jobEditFragment = JobEditFragment().apply {
            arguments = bundle
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, jobEditFragment)
            .addToBackStack(null)
            .commit()
    }
}