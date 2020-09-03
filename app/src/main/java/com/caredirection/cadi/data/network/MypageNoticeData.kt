package com.caredirection.cadi.data.network

data class MypageNoticeData(
    val status:Int,
    val message:String,
    val data: List<MypageNoticeItem>
)

data class MypageNoticeItem(
    val notice_idx : Int,
    val notice_title : String,
    val notice_time : String
)