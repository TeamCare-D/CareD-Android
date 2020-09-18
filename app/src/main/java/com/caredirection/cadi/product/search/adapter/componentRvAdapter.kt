package com.caredirection.cadi.product.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.IngredientList

class componentRvAdapter(val context: Context): RecyclerView.Adapter<componentRvAdapter.nameRvViewHolder>() {
    val items = mutableListOf<IngredientList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nameRvViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_search_name, parent, false)
        return nameRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: nameRvViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class nameRvViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_product_search_name_title: TextView = view.findViewById(R.id.txt_rv_item_product_search_name_title)

        fun bind(data: IngredientList){
            txt_rv_item_product_search_name_title.text = data.ingredient_name
        }
    }
}