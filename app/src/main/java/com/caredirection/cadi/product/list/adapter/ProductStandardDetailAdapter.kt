package com.caredirection.cadi.product.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.criterionDescription3Data

class ProductStandardDetailAdapter: RecyclerView.Adapter<ProductStandardDetailAdapter.ProductStandardDetailHolder>() {
    val itemsSubtitle = mutableListOf<String>()
    val itemsContent = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductStandardDetailHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_fragment_product_list_detail, parent, false)
        return ProductStandardDetailHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsSubtitle.size
    }

    override fun onBindViewHolder(holder: ProductStandardDetailHolder, position: Int) {
        holder.bind(itemsSubtitle[position], itemsContent[position])
    }

    inner class ProductStandardDetailHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_fragment_product_list_detail_title: TextView = itemView.findViewById(R.id.txt_rv_item_fragment_product_list_detail_title)
        val txt_rv_item_fragment_product_list_detail_content: TextView = itemView.findViewById(R.id.txt_rv_item_fragment_product_list_detail_content)

        fun bind(itemSubtitle: String, itemContent: String){
            txt_rv_item_fragment_product_list_detail_title.text = itemSubtitle
            txt_rv_item_fragment_product_list_detail_content.text = itemContent
        }
    }
}