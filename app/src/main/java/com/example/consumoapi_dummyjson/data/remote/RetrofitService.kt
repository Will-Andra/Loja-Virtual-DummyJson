package com.example.consumoapi_dummyjson.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor()) //addicionado apenas para estudo. Api não demanda token
            .build()
    }


    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val dummyAPI: DummyAPI by lazy {
        retrofit.create(DummyAPI::class.java)
    }
}