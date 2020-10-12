package com.caredirection.cadi.cadizone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.MagazineTagRvAdapter
import com.caredirection.cadi.networkdata.MagazineListData

class MagazineRecyclerViewAdapter :
    ListAdapter<MagazineListData, MagazineRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<MagazineListData>() {
        override fun areItemsTheSame(oldItem: MagazineListData, newItem: MagazineListData): Boolean {
            return oldItem.magazine_idx == newItem.magazine_idx
        }

        override fun areContentsTheSame(oldItem: MagazineListData, newItem: MagazineListData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_magazine_ingredient, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).magazine_title
        holder.bind(getItem(position).hashtag_name)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rvMagazineTagAdapter = MagazineTagRvAdapter()
        val rv_rv_item_magazine_ingredient: RecyclerView = itemView.findViewById(R.id.rv_rv_item_magazine_ingredient)

        val text: TextView = itemView.findViewById(R.id.txt_rv_item_magazine_ingredient)

        fun bind(items: MutableList<String>){
            rvMagazineTagAdapter.items.addAll(items)
            rv_rv_item_magazine_ingredient.adapter = rvMagazineTagAdapter
        }
    }
}