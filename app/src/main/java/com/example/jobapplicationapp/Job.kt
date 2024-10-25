package com.example.jobapplicationapp

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_table")
data class Job(
    @PrimaryKey(autoGenerate = true) val jobId: Int = 0,
    val jobTitle: String,
    val organizationName: String,
    val jobCategory: String,
    val skillsRequired: String,
    val status: String,
    val date: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(jobId)
        parcel.writeString(jobTitle)
        parcel.writeString(organizationName)
        parcel.writeString(jobCategory)
        parcel.writeString(skillsRequired)
        parcel.writeString(status)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Job> {
        override fun createFromParcel(parcel: Parcel): Job {
            return Job(parcel)
        }

        override fun newArray(size: Int): Array<Job?> {
            return arrayOfNulls(size)
        }
    }
}
