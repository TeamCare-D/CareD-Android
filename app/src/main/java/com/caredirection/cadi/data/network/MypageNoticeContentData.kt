package com.caredirection.cadi.data.network

data class MypageNoticeContentData(
    val status:Int,
    val message:String,
    val data: MypageNoticeContentItem
)

data class MypageNoticeContentItem(
    val notice_idx : Int,
    val notice_title : String,
    val notice_time : String,
    val notice_content : String
)