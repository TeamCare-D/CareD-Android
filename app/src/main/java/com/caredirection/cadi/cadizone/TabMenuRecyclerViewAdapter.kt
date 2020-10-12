package com.caredirection.cadi.cadizone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.product.search.adapter.ingredientIdxData

class TabMenuRecyclerViewAdapter : ListAdapter<ingredientIdxData, TabMenuRecyclerViewAdapter.ViewHolder>(DiffTool()) {

    private class DiffTool : DiffUtil.ItemCallback<ingredientIdxData>() {
        override fun areItemsTheSame(oldItem: ingredientIdxData, newItem: ingredientIdxData): Boolean {
            return oldItem.ingredient_idx == newItem.ingredient_idx
        }

        override fun areContentsTheSame(oldItem: ingredientIdxData, newItem: ingredientIdxData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_product_search_symptom_name, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = getItem(position).nameItem
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text: TextView = itemView.findViewById(R.id.txt_rv_item_product_search_name)
    }
}

data class Ingredient(
    val name: String,
    val idx: Int
)