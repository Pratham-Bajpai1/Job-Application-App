package com.example.jobapplicationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class EmployeeRegistrationFragment : Fragment() {

    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_employee_registration, container, false)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        val btnRegister = view.findViewById<Button>(R.id.btn_register)

        btnRegister.setOnClickListener {
            val organizationName = view.findViewById<EditText>(R.id.organization_name).text.toString()
            val address = view.findViewById<EditText>(R.id.address).text.toString()
            val firstName = view.findViewById<EditText>(R.id.first_name).text.toString()
            val lastName = view.findViewById<EditText>(R.id.last_name).text.toString()
            val email = view.findViewById<EditText>(R.id.email).text.toString()
            val password = view.findViewById<EditText>(R.id.password).text.toString()

            // Validate input before registering
            if (organizationName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val employee = Employee(
                    organizationName = organizationName,
                    address = address,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
                employeeViewModel.registerEmployee(employee)
                Toast.makeText(activity, "Employee Registered", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(activity, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}