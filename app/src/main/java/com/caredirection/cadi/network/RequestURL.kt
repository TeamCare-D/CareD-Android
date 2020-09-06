package com.caredirection.cadi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestURL{
    private const val BASE_URL ="http://3.34.153.207:8080"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service : RequestInterface = retrofit.create(RequestInterface::class.java)
}