package com.example.theprofileorderexperiment.Model.Repository

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://hinge-ue1-dev-cli-android-homework.s3-website-us-east-1.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}

abstract class CallbackWithRetry<T>(call: Call<T>) : Callback<T?> {
    private val call: Call<T> = call
    var retryCount = 0
    fun onFailure(t: Throwable?) {
        t?.localizedMessage?.let { Log.e(call.javaClass.simpleName, it) }
        if (retryCount < TOTAL_RETRIES) {
            Log.v(
                call.javaClass.simpleName,
                "Retrying... ($retryCount out of $TOTAL_RETRIES)"
            )
            retry()
        }
    }

    fun retry() {
        retryCount++
        call.clone().enqueue(this)
    }

    companion object {
        const val TOTAL_RETRIES = 3
    }

}

object CallUtils {
    fun <T> enqueueWithRetry(
        call: Call<T>,
        callback: Callback<T>
    ) {
        call.enqueue(object : CallbackWithRetry<T>(call) {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                } else if (retryCount < TOTAL_RETRIES) {
                    super.retry()
                }
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                Log.d(call.javaClass.simpleName, "came here failure")
                super.onFailure(t)
                callback.onFailure(call, t)
            }
        })
    }
}