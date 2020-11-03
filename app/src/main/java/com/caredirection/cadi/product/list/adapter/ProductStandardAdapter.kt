package com.caredirection.cadi.product.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class ProductStandardAdapter: RecyclerView.Adapter<ProductStandardAdapter.ProductStandardHolder>() {

    val items = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductStandardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_fragment_product_list_detail_standard, parent, false)
        return ProductStandardHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductStandardHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductStandardHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_fragment_product_list_detail_standard: TextView = itemView.findViewById(R.id.txt_rv_item_fragment_product_list_detail_standard)

        fun bind(item: String){
            txt_rv_item_fragment_product_list_detail_standard.text = item
        }
    }
}