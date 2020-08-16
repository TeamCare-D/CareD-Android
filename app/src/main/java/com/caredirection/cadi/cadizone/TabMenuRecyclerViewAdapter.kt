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

class TabMenuRecyclerViewAdapter :
    ListAdapter<Ingredient, TabMenuRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.idx == newItem.idx
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_top_dic, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).name
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: ConstraintLayout = itemView.findViewById(R.id.cl_container)
        val text: TextView = itemView.findViewById(R.id.tv_ingredient)
    }
}

data class Ingredient(
    val name: String,
    val idx: Int
)