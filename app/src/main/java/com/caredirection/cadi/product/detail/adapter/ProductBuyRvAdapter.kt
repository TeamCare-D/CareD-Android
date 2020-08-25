package com.caredirection.cadi.product.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class ProductBuyRvAdapter: RecyclerView.Adapter<ProductBuyRvAdapter.ProductBuyRvHolder>() {

    val items = mutableListOf<ProductBuyData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductBuyRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_product_buy, parent, false)
        return ProductBuyRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductBuyRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductBuyRvHolder(view: View): RecyclerView.ViewHolder(view){

        val txt_rv_item_product_buy_shop: TextView = itemView.findViewById(R.id.txt_rv_item_product_buy_shop)
        val txt_rv_item_product_buy_price: TextView = itemView.findViewById(R.id.txt_rv_item_product_buy_price)


        fun bind(item: ProductBuyData){
            txt_rv_item_product_buy_shop.text = item.shop
            txt_rv_item_product_buy_price.text = item.price
        }
    }
}
data class ProductBuyData(
    val shop: String,
    val price: String,
    val img: String
)