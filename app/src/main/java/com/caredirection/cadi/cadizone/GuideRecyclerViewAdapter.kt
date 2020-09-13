package com.caredirection.cadi.cadizone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.MagazineGuideDataList

class GuideRecyclerViewAdapter :
    ListAdapter<MagazineGuideDataList, GuideRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<MagazineGuideDataList>() {
        override fun areItemsTheSame(oldItem: MagazineGuideDataList, newItem: MagazineGuideDataList): Boolean {
            return oldItem.magazine_idx == newItem.magazine_idx
        }

        override fun areContentsTheSame(oldItem: MagazineGuideDataList, newItem: MagazineGuideDataList): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_guide, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).magazine_title
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.txt_rv_item_guide_title)
    }
}
