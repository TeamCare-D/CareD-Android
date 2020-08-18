package com.caredirection.cadi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class ProductMagazineRvAdapter(private val context: Context) : RecyclerView.Adapter<ProductMagazineRvAdapter.ProductMagazineRvHolder>() {

    val items = mutableListOf<ProductMagazineData>()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductMagazineRvHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_magazine_ingredient, parent, false)
        return ProductMagazineRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductMagazineRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductMagazineRvHolder(view: View) : RecyclerView.ViewHolder(view){

        val rv_rv_item_magazine_ingredient : RecyclerView = view.findViewById(R.id.rv_rv_item_magazine_ingredient)
        val rvMagazineTagAdapter = MagazineTagRvAdapter(itemView.context)

        val txt_rv_item_magazine_ingredient: TextView = itemView.findViewById(R.id.txt_rv_item_magazine_ingredient)



        fun bind(items: ProductMagazineData){
            txt_rv_item_magazine_ingredient.text = items.title


            rv_rv_item_magazine_ingredient.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv_rv_item_magazine_ingredient.adapter = rvMagazineTagAdapter
            rvMagazineTagAdapter.items.addAll(items.tag)

        }
    }
}

data class ProductMagazineData(
    var title: String,
    var tag: List<String>
)
