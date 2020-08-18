package com.caredirection.cadi.cadizone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class MagazineRecyclerViewAdapter :
    ListAdapter<Magazine, MagazineRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<Magazine>() {
        override fun areItemsTheSame(oldItem: Magazine, newItem: Magazine): Boolean {
            return oldItem.idx == newItem.idx
        }

        override fun areContentsTheSame(oldItem: Magazine, newItem: Magazine): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_magazine, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).name
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: ConstraintLayout = itemView.findViewById(R.id.cl_rv_container)
        val text: TextView = itemView.findViewById(R.id.tv_rv_magazine_title)
        val img: ImageView = itemView.findViewById(R.id.iv_rv_magazine_img)
        val list: RecyclerView = itemView.findViewById(R.id.rv_rv_magazine_list)
    }
}

data class Magazine(
    val name: String,
    val tag: List<String>,
    val img: String,
    val idx: Int
)