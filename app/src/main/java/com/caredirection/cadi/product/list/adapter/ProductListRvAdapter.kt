package com.caredirection.cadi.product.list.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.product.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_product_list.view.*

class ProductListRvAdapter(private val context: Context) : RecyclerView.Adapter<ProductListRvAdapter.ProductListRvHolder>(){

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListRvHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_list, parent, false)
        return ProductListRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductListRvHolder, position: Int) {
        holder.bind()
    }

    inner class ProductListRvHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(){
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                context.startActivity(intent)
            }


        }

    }
}