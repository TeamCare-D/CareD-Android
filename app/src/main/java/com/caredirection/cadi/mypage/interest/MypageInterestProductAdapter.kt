package com.caredirection.cadi.mypage.interest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.MypageInterestProductListItem

class MypageInterestProductAdapter(private val context: Context) : RecyclerView.Adapter<MypageInterestProductViewHolder>(){
    var data: List<MypageInterestProductListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageInterestProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_mypage_interest_product, parent, false)

        return MypageInterestProductViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MypageInterestProductViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}