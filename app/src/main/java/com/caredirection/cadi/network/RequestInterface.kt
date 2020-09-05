package com.caredirection.cadi.network

import com.caredirection.cadi.data.network.MypageInterestData
import com.caredirection.cadi.data.network.MypageNoticeContentData
import com.caredirection.cadi.data.network.MypageNoticeData
import com.caredirection.cadi.data.network.MypageRequestData
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    // 마이페이지 - 관심 제품 리스트 조회
    @GET("/product/like")
    fun getInterestList(
        @Header("token")token: String
    ): Call<MypageInterestData>

    // 마이페이지 - 공지사항 리스트 조회
    @GET("/notice")
    fun getNoticeList(
    ): Call<MypageNoticeData>

    // 마이페이지 - 공지사항 상세 내용 조회
    @GET("/notice/{notice_idx}")
    fun getNoticeContent(
        @Path("notice_idx")notice_idx:Int
    ):Call<MypageNoticeContentData>

    // 마이페이지 - 제품 등록 요청
    @FormUrlEncoded
    @POST("/request")
    fun postProductRequest(
        @Field("productName")productName : String,
        @Header("token")token: String
    ) : Call<MypageRequestData>
}