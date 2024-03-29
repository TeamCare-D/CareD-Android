package com.caredirection.cadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.MagazineDirections

class MagazineMarketingAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val marketingItems = mutableListOf<MagazineDirections>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_product_magazine_marketing, parent, false)
        return MagazineMarketingHolder(view)
    }

    override fun getItemCount(): Int {
        return marketingItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MagazineMarketingHolder){
            holder.bind(marketingItems[position])
        }
    }

    inner class MagazineMarketingHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_product_magazine_marketing: TextView = view.findViewById(R.id.txt_rv_item_product_magazine_marketing)
        val txt_rv_item_product_magazine_marketing_title: TextView = view.findViewById(R.id.txt_rv_item_product_magazine_marketing_title)

        fun bind(item: MagazineDirections){
            txt_rv_item_product_magazine_marketing.text = item.magazine_title
            txt_rv_item_product_magazine_marketing_title.text = item.hashtag_name[0]
        }

    }
}