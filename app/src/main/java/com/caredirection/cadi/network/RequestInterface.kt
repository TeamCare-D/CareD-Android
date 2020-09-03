package com.caredirection.cadi.network

import com.caredirection.cadi.data.network.MypageNoticeData
import retrofit2.Call
import retrofit2.http.GET

interface RequestInterface {

    @GET("/notice")
    fun getNoticeList(
    ): Call<MypageNoticeData>
}