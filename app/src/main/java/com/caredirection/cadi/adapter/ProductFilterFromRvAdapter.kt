package com.caredirection.cadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class ProductFilterFromRvAdapter: RecyclerView.Adapter<ProductFilterFromRvAdapter.ProductFilterFromRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductFilterFromRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_product_filter_form_category, parent, false)
        return ProductFilterFromRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductFilterFromRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductFilterFromRvHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_product_filter_form_category: TextView = itemView.findViewById(R.id.txt_rv_item_product_filter_form_category)

        fun bind(item: String){
            txt_rv_item_product_filter_form_category.text = item
        }
    }
}