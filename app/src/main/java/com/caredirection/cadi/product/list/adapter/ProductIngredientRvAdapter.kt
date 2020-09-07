package com.caredirection.cadi.product.list.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R


class ProductIngredientRvAdapter: RecyclerView.Adapter<ProductIngredientRvAdapter.ProductIngredientRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductIngredientRvHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_product_list_standard, parent, false)
        return ProductIngredientRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductIngredientRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductIngredientRvHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_product_list_standard: TextView = itemView.findViewById(R.id.txt_rv_item_product_list_standard)

        fun bind(text: String) {
            txt_rv_item_product_list_standard.text = text
        }
    }
}