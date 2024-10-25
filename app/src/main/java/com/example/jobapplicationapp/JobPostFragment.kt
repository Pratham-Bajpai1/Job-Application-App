package com.example.jobapplicationapp

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.util.Calendar

class JobPostFragment : Fragment() {

    private lateinit var jobViewModel: JobViewModel
    private lateinit var statusSpinner: Spinner
    private lateinit var selectedDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_post, container, false)

        jobViewModel = ViewModelProvider(this).get(JobViewModel::class.java)

        statusSpinner = view.findViewById(R.id.spinner_status)
        setupStatusSpinner()

        val btnAddJob = view.findViewById<Button>(R.id.btn_add_job)
        btnAddJob.setOnClickListener {
            addJobToDatabase(view)
        }

        val btnSelectDate = view.findViewById<Button>(R.id.btn_select_date)
        btnSelectDate.setOnClickListener {
            showDatePicker(view)
        }

        // Return the inflated view
        return view
    }

    // Set up the date picker dialog
    private fun showDatePicker(view : View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireActivity(), { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            view.findViewById<TextView>(R.id.select_date).text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun setupStatusSpinner() {
        val statusOptions = arrayOf("Applied", "Interview", "Offer", "Rejected")
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, statusOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner.adapter = adapter
    }

    private fun addJobToDatabase(view : View) {
        val jobTitle = view.findViewById<EditText>(R.id.job_title).text.toString()
        val organizationName =  view.findViewById<EditText>(R.id.job_organization).text.toString()
        val jobCategory = view.findViewById<EditText>(R.id.job_category).text.toString()
        val skillsRequired = view.findViewById<EditText>(R.id.skills_required).text.toString()
        val status = statusSpinner.selectedItem.toString()

        if (jobTitle.isNotEmpty() && organizationName.isNotEmpty() && skillsRequired.isNotEmpty() && ::selectedDate.isInitialized) {
            val job = Job(
                jobTitle = jobTitle,
                organizationName = organizationName,
                jobCategory = jobCategory,
                skillsRequired = skillsRequired,
                status = status,
                date = selectedDate
            )
            jobViewModel.insertJob(job)
            Toast.makeText(activity, "Job Added Successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Please fill all required fields", Toast.LENGTH_SHORT).show()
        }
    }
}