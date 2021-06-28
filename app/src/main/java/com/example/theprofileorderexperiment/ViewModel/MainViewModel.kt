package com.example.theprofileorderexperiment.ViewModel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theprofileorderexperiment.Model.DataModel.ConfigResponse
import com.example.theprofileorderexperiment.Model.DataModel.User
import com.example.theprofileorderexperiment.Model.DataModel.UserResponse
import com.example.theprofileorderexperiment.Model.Repository.CallUtils
import com.example.theprofileorderexperiment.Model.Repository.RetrofitClient
import com.example.theprofileorderexperiment.Model.Repository.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val request = RetrofitClient.buildService(RetrofitInterface::class.java)
    var configLiveData = MutableLiveData<Array<String>>()
    var usersLiveData = MutableLiveData<Array<User>>()

    fun getConfig(activity: Activity?): LiveData<Array<String>> {
        if (configLiveData.value == null) {
            CallUtils.enqueueWithRetry(request.getConfig(), object : Callback<ConfigResponse> {
                override fun onFailure(call: Call<ConfigResponse>, t: Throwable) {
                    Log.d(call.javaClass.simpleName, "Failure occurred " + t.localizedMessage)
                    activity?.finish()
                }

                override fun onResponse(
                    call: Call<ConfigResponse>,
                    response: Response<ConfigResponse>
                ) {
                    if (response.isSuccessful) {
                        configLiveData.value = response.body()?.profile
                    }
                }

            })
        }
        return configLiveData
    }

    fun getUsers(activity: Activity?): LiveData<Array<User>> {
        if (usersLiveData.value == null) {
            CallUtils.enqueueWithRetry(request.getUsers(), object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                    Log.d(call.javaClass.simpleName, "Failure occurred " + t.localizedMessage)
                    activity?.finish()
                }

                override fun onResponse(
                    call: Call<UserResponse?>,
                    response: Response<UserResponse?>
                ) {
                    if (response.isSuccessful) {
                        usersLiveData.value = response.body()?.users
                    }
                }
            })
        }
        return usersLiveData
    }
}