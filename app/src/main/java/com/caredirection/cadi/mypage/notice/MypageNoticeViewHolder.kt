package com.caredirection.cadi.mypage.notice

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.MypageNoticeListItem

class MypageNoticeViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val txtNoticeDay : TextView = view.findViewById(R.id.txt_mypage_notice_day)
    private val txtNoticeTitle : TextView = view.findViewById(R.id.txt_mypage_notice_title)

    fun onBind(notice: MypageNoticeListItem){
        txtNoticeDay.text = notice.day
        txtNoticeTitle.text = notice.title
    }
}