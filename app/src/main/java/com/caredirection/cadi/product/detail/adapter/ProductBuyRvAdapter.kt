package com.caredirection.cadi.product.detail.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.StoreInfoData
import com.caredirection.cadi.product.detail.ActivityProductDetailWeb

class ProductBuyRvAdapter(private val context: Context): RecyclerView.Adapter<ProductBuyRvAdapter.ProductBuyRvHolder>() {

    val items = mutableListOf<StoreInfoData>()

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


        fun bind(item: StoreInfoData){
            txt_rv_item_product_buy_shop.text = item.store_name
            txt_rv_item_product_buy_price.text = item.store_product_price.toString()

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ActivityProductDetailWeb::class.java)

                intent.putExtra("link", item.store_link)
                context.startActivity(intent)
            }

        }
    }
}