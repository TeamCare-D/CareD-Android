package com.caredirection.cadi.mypage.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.MypageNoticeListItem

class MypageNoticeAdapter (private val context: Context) : RecyclerView.Adapter<MypageNoticeViewHolder>(){
    var data: List<MypageNoticeListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_mypage_notice, parent, false)

        return MypageNoticeViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MypageNoticeViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}