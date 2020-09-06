package com.caredirection.cadi.mypage.take

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvTakeListItem

class MypageTakeProductAdapter(private val context: Context) : RecyclerView.Adapter<MypageTakeProductViewHolder>(){
    var data: List<RvTakeListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageTakeProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_list,parent,false)

        return MypageTakeProductViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MypageTakeProductViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}