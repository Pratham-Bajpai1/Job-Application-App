package com.example.jobapplicationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class RegistrationActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        val btnRegister = findViewById<Button>(R.id.btn_register)

        btnRegister.setOnClickListener {
            val organizationName = findViewById<EditText>(R.id.organization_name).text.toString()
            val address = findViewById<EditText>(R.id.address).text.toString()
            val firstName = findViewById<EditText>(R.id.first_name).text.toString()
            val lastName = findViewById<EditText>(R.id.last_name).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

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
                Toast.makeText(this, "Employee Registered", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}