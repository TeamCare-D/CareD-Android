package com.caredirection.cadi.product.search.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.contents.ContentActivity


class SymptomIngredientRvAdapter(val context : Context, var items: MutableList<ingredientIdxData>): RecyclerView.Adapter<SymptomIngredientRvAdapter.testRvVeiwHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): testRvVeiwHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_search_symptom_name, parent, false)
        return testRvVeiwHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SymptomIngredientRvAdapter.testRvVeiwHolder, position: Int) {
        holder.bind(items[position].nameItem, position)
    }

    inner class testRvVeiwHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_product_search_name: TextView = view.findViewById(R.id.txt_rv_item_product_search_name)

        fun bind(text: String, position: Int){
            txt_rv_item_product_search_name.text = text


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ContentActivity::class.java)

                intent.putExtra("ingredient_idx", items[position].ingredient_idx)
                context.startActivity(intent)
            }
        }
    }
}