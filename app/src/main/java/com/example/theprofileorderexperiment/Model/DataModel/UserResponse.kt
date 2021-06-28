package com.example.theprofileorderexperiment.Model.DataModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(val users: Array<User>) : Parcelable