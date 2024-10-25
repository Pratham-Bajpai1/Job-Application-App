# Job Application Management App

This Android application provides an efficient solution for managing employee registrations, job postings, and job application tracking. Designed to streamline job management, the app allows for CRUD operations, filters, and navigation through a user-friendly interface, utilizing the MVVM architecture pattern and SQLite for local data storage.

---

## Features

### 1. Employee Registration:

* Allows an organization to register its details, which remains persistent across sessions.
* Enables smooth job posting and management if an organization is already registered.

![Screenshot_2024-10-25-13-32-43-105_com example jobapplicationapp](https://github.com/user-attachments/assets/7c5cd15b-36ab-4139-be48-67f45563c15f)

### 2. Job Posting:

* An organization can create job postings with detailed information such as job title, organization name, category, required skills, and status.
* Newly posted jobs are immediately available in the job list.

![Screenshot_2024-10-25-13-32-52-485_com example jobapplicationapp](https://github.com/user-attachments/assets/6d42a33e-1ac8-4853-acd8-9704b552c0d7)


### 3. Job Application Management:

* Displays a list of job postings with filtering functionality.
![Screenshot_2024-10-25-13-33-04-260_com example jobapplicationapp](https://github.com/user-attachments/assets/93cbd52b-dab3-40e0-a32f-5d2da6c50712)

* Allows users to edit or delete specific job posts.
![Screenshot_2024-10-25-13-33-07-364_com example jobapplicationapp](https://github.com/user-attachments/assets/bbd9d225-883d-41e4-b0c0-5610d232d162)


### 4. MVVM Architecture:

* The project follows the MVVM pattern, ensuring separation of concerns, testability, and maintainability.
* ViewModel and Repository handle data-related logic, while Fragments display data and handle user interactions.

---

## Technical Stack

* **Language:** Kotlin
* **Architecture:** MVVM (Model-View-ViewModel)
* **Database:** SQLite via Room Database
* **UI Components:** RecyclerView, Spinner, BottomNavigationView
* **Navigation:** Fragment navigation for seamless UI experience

---

## Project Structure

```
├── MainActivity.kt                # Hosts the BottomNavigationView for fragment navigation
├── EmployeeRegistrationFragment.kt # Handles employee registration functionality
├── JobListFragment.kt              # Displays list of jobs with filtering capabilities
├── JobPostFragment.kt              # Manages job posting creation
├── JobEditFragment.kt              # Facilitates job post editing
├── JobAdapter.kt                   # RecyclerView adapter for displaying job posts
├── ViewModel/
│   ├── EmployeeViewModel.kt
│   └── JobViewModel.kt
├── Repository/
│   ├── EmployeeRepository.kt
│   └── JobRepository.kt
├── Database/
│   ├── Employee.kt                 # Entity class for Employee data
│   ├── Job.kt                      # Entity class for Job postings
│   └── EmployeeDatabase.kt         # Database setup with Room
└── layouts/
    ├── activity_main.xml
    ├── fragment_employee_registration.xml
    ├── fragment_job_list.xml
    ├── fragment_job_post.xml
    └── job_item.xml                # Layout for each job item in the list
```
---

## Installation & Setup

**1. Clone this repository:**
```
git clone [repository-link]
cd JobApplicationApp
```

**2. Open the project in Android Studio.**

**3. Run the app on an emulator or connected device.**

---

## Usage
**1. Register Employee:** Complete the registration form for an organization. Once registered, the organization can create job postings.

**2.Add Job Post:** In the "Add Job Post" section, provide details for a new job posting.

**3. View & Manage Job Posts:** Access the "Job List" to view all job postings, with options to filter, edit, or delete any post.

---

## Future Improvements:

- **Enhanced Filtering:** Additional filtering options such as job category and skills.
- **Push Notifications:** Notify employees about new job postings or status updates.
- **Data Backup:** Implement backup and restore functionality for job and employee data.

---