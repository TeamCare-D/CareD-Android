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

class GuideRecyclerViewAdapter :
    ListAdapter<Guide, GuideRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<Guide>() {
        override fun areItemsTheSame(oldItem: Guide, newItem: Guide): Boolean {
            return oldItem.idx == newItem.idx
        }

        override fun areContentsTheSame(oldItem: Guide, newItem: Guide): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_guide, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).name
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: ConstraintLayout = itemView.findViewById(R.id.cl_rv_container)
        val text: TextView = itemView.findViewById(R.id.tv_rv_guide_title)
    }
}

data class Guide(
    val name: String,
    val idx: Int
)