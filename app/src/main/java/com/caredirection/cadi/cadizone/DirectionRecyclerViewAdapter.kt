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

class DirectionRecyclerViewAdapter :
    ListAdapter<Direction, DirectionRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<Direction>() {
        override fun areItemsTheSame(oldItem: Direction, newItem: Direction): Boolean {
            return oldItem.idx == newItem.idx
        }

        override fun areContentsTheSame(oldItem: Direction, newItem: Direction): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_direction, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).name
        holder.tag.text = getItem(position).tag
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: ConstraintLayout = itemView.findViewById(R.id.cl_rv_container)
        val text: TextView = itemView.findViewById(R.id.tv_rv_direction_title)
        val tag: TextView = itemView.findViewById(R.id.tv_rv_direction_tag)
    }
}

data class Direction(
    val name: String,
    val tag: String,
    val idx: Int
)