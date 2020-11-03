package com.caredirection.cadi.product.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.MagazineMarketingAdapter
import com.caredirection.cadi.adapter.MagazineTagRvAdapter
import com.caredirection.cadi.networkdata.MagazineDirections
import com.caredirection.cadi.networkdata.MagazineListData

class ProductMagazineRvAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //큰거
    val items = mutableListOf<MagazineListData>()
    //작은거
    val marketingItems = mutableListOf<MagazineDirections>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.rv_item_magazine_ingredient -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_magazine_ingredient, parent, false)
                ProductMagazineRvHolder(view)
            }
            R.layout.view_rv_magazine_marketing -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_rv_magazine_marketing, parent, false)
                MagezineMarketing(view)
            }
            else -> throw IllegalArgumentException("ViewType [$viewType] is unexpected")
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            1 -> R.layout.view_rv_magazine_marketing
            else -> R.layout.rv_item_magazine_ingredient
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductMagazineRvHolder) {
            holder.bind(items[position])
        }
        if (holder is MagezineMarketing) {
            holder.bind(marketingItems)
        }
    }

    inner class ProductMagazineRvHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rv_rv_item_magazine_ingredient: RecyclerView =
            view.findViewById(R.id.rv_rv_item_magazine_ingredient)
        val rvMagazineTagAdapter = MagazineTagRvAdapter()
        val txt_rv_item_magazine_ingredient: TextView =
            view.findViewById(R.id.txt_rv_item_magazine_ingredient)

        fun bind(items: MagazineListData) {
            Log.d("String", items.magazine_title)
            txt_rv_item_magazine_ingredient.text = items.magazine_title
            rv_rv_item_magazine_ingredient.adapter = rvMagazineTagAdapter
            rvMagazineTagAdapter.items.addAll(items.hashtag_name)
        }
    }

    inner class MagezineMarketing(view: View) : RecyclerView.ViewHolder(view) {

        val magazineMarketingAdapter =
            MagazineMarketingAdapter()
        val rv_magazine_marketing: RecyclerView = view.findViewById(R.id.rv_magazine_marketing)

        fun bind(item: MutableList<MagazineDirections>) {
            magazineMarketingAdapter.marketingItems.addAll(item)
            rv_magazine_marketing.adapter = magazineMarketingAdapter
        }
    }
}
