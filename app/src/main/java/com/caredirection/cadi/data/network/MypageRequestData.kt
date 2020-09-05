package com.caredirection.cadi.data.network

data class MypageRequestData(
    val status : Int,
    val message: String,
    val data : MypageRequestItem
)

data class MypageRequestItem(
    val productName : String,
    val token : String
)