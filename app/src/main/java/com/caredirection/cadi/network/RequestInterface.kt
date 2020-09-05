package com.caredirection.cadi.network

import com.caredirection.cadi.data.network.MypageNoticeContentData
import com.caredirection.cadi.data.network.MypageNoticeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestInterface {

    @GET("/notice")
    fun getNoticeList(
    ): Call<MypageNoticeData>

    @GET("/notice/{notice_idx}")
    fun getNoticeContent(
        @Path("notice_idx")notice_idx:Int
    ):Call<MypageNoticeContentData>
}