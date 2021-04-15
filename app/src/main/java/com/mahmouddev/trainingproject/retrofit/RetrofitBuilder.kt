package com.mahmouddev.trainingproject.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {


    private fun initRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val apiService = initRetrofit().create(ApiService::class.java)
}