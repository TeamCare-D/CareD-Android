package com.caredirection.cadi.mypage.notice

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.MypageNoticeListItem

class MypageNoticeViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val txtNoticeDay : TextView = view.findViewById(R.id.txt_mypage_notice_day)
    private val txtNoticeTitle : TextView = view.findViewById(R.id.txt_mypage_notice_title)
    private val imgNoticeBackGround : ImageView = view.findViewById(R.id.img_mypage_notice_background)
    private val txtNoticeContent : TextView = view.findViewById(R.id.txt_mypage_notice_content)

    fun onBind(notice: MypageNoticeListItem, isExpanded: Boolean){
        txtNoticeDay.text = notice.day
        txtNoticeTitle.text = notice.title

        changeVisibility(isExpanded)
    }

    private fun changeVisibility(isExpanded : Boolean){
        imgNoticeBackGround.visibility = if(isExpanded) View.VISIBLE else View.GONE
        txtNoticeContent.visibility = if(isExpanded) View.VISIBLE else View.GONE
    }
}