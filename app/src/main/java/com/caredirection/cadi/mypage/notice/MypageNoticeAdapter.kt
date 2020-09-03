package com.caredirection.cadi.mypage.notice

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.RvMypageNoticeListItem

class MypageNoticeAdapter (private val context: Context) : RecyclerView.Adapter<MypageNoticeViewHolder>(){
    var data: List<RvMypageNoticeListItem> = listOf()

    private var selectedItem = SparseBooleanArray()
    private var noticePosition = -1

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
        holder.onBind(data[position], selectedItem.get(position))

        setItemClickListener(holder,position)
    }

    private fun setItemClickListener(holder: MypageNoticeViewHolder, position: Int){
        holder.itemView.setOnClickListener {
            if(selectedItem.get(position)){
                selectedItem.delete(position)
            }
            else{
                selectedItem.delete(noticePosition)
                selectedItem.put(position, true)
            }

            if(noticePosition != -1){
                notifyItemChanged(noticePosition)
            }
            notifyItemChanged(position)

            noticePosition = position
        }
    }
}