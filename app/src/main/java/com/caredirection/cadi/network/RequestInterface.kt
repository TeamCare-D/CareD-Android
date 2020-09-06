package com.caredirection.cadi.network

import com.caredirection.cadi.data.network.*
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    // 복용제품등록, 마이페이지 - 복용 제품 리스트 조회
    @GET("/dose/product")
    fun getTakeList(
        @Header("token")token: String
    ): Call<TakeProductData>

    // 복용제품등록, 마이페이지 - 복용 제품 취소
    @DELETE("/dose/{product_idx}")
    fun deleteTakeProduct(
        @Path("product_idx")product_idx:Int,
        @Header("token")token: String
    ) : Call<DeleteTakeData>

    // 복용제품등록, 마이페이지 - 복용 제품 검색
    @GET("/dose/search")
    fun getTakeSearchList(
        @Query("query")keyword:String,
        @Header("token")token: String
    ): Call<TakeSearchData>

    // 복용제품등록, 마이페이지 - 복용 제품 등록
    @FormUrlEncoded
    @POST("/dose/{product_idx}")
    fun postTakeProduct(
        @Path("product_idx")product_idx:Int,
        @Header("token")token: String
    ) : Call<RegisterTakeProductData>

    // 마이페이지 - 관심 제품 리스트 조회
    @GET("/product/like")
    fun getInterestList(
        @Header("token")token: String
    ): Call<MypageInterestData>

    // 마이페이지 - 관심 제품 취소
    @DELETE("/product/{product_idx}/like")
    fun deleteInterestProduct(
        @Path("product_idx")product_idx:Int,
        @Header("token")token: String
    ) : Call<MypageDeleteInterestData>

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