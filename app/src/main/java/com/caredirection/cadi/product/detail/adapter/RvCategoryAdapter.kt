package com.caredirection.cadi.product.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class RvCategoryAdapter(val context: Context): RecyclerView.Adapter<RvCategoryAdapter.RvCategoryHoler>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvCategoryHoler {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_detail_ingredient_category, parent, false)
        return RvCategoryHoler(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RvCategoryHoler, position: Int) {
        holder.bind(items[position])
    }

    inner class RvCategoryHoler(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_detail_ingredient_category: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_category)

        fun bind(text: String){
            txt_rv_item_detail_ingredient_category.text = text
        }
    }
}