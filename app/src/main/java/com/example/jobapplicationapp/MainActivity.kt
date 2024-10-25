package com.example.jobapplicationapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default fragment (Employee Registration Fragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, EmployeeRegistrationFragment())
            .commit()

        // Handle BottomNavigationView item clicks
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_register -> {
                    // Show Employee Registration Fragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, EmployeeRegistrationFragment())
                        .commit()
                    true
                }
                R.id.nav_add_job -> {
                    // Show Job Post Fragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, JobPostFragment())
                        .commit()
                    true
                }
                R.id.nav_show_jobs -> {
                    // Show Job List Fragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, JobListFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}