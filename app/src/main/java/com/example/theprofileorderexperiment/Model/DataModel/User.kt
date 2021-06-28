package com.example.theprofileorderexperiment.Model.DataModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    val name: String,
    val gender: String,
    val about: String,
    val hobbies: Array<String>,
    val school: String,
    val photo: String
) : Parcelable