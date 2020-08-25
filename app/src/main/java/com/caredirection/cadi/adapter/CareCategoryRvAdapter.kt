package com.caredirection.cadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class CareCategoryRvAdapter: RecyclerView.Adapter<CareCategoryRvAdapter.CareCategoryRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareCategoryRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_care_category, parent, false)
        return CareCategoryRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CareCategoryRvHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class CareCategoryRvHolder(view: View): RecyclerView.ViewHolder(view){

        val txt_rv_item_care_category: TextView = itemView.findViewById(R.id.txt_rv_item_care_category)
        val bar_rv_item_care_category: TextView = itemView.findViewById(R.id.bar_rv_item_care_category)


        fun bind(item: String, position: Int){
            txt_rv_item_care_category.text = item

        }
    }
}