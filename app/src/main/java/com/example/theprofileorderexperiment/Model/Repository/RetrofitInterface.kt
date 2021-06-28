package com.example.theprofileorderexperiment.Model.Repository

import com.example.theprofileorderexperiment.Model.DataModel.ConfigResponse
import com.example.theprofileorderexperiment.Model.DataModel.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("/users")
    fun getUsers(): Call<UserResponse>

    @GET("/config")
    fun getConfig(): Call<ConfigResponse>
}