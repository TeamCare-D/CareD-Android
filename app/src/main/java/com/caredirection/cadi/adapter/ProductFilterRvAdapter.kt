package com.caredirection.cadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class ProductFilterRvAdapter: RecyclerView.Adapter<ProductFilterRvAdapter.ProductFilterRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductFilterRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_product_filter, parent, false)
        return ProductFilterRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductFilterRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductFilterRvHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_product_filter: TextView = itemView.findViewById(R.id.txt_rv_item_product_filter)
        val img_rv_item_product_filter: CheckedTextView = itemView.findViewById(R.id.img_rv_item_product_filter)


        fun bind(item: String){
            txt_rv_item_product_filter.text = item
            itemView.setOnClickListener {
                img_rv_item_product_filter.isChecked = !img_rv_item_product_filter.isChecked

            }
        }

    }
}