package com.caredirection.cadi.product.list.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.ProductInfo
import com.caredirection.cadi.product.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_product_list.view.*

class ProductListRvAdapter(private val context: Context) :
    RecyclerView.Adapter<ProductListRvAdapter.ProductListRvHolder>() {

    val items = mutableListOf<ProductInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListRvHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.rv_item_product_list, parent, false)
        return ProductListRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductListRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ProductListRvHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_rv_item_product_list_brand: TextView = itemView.findViewById(R.id.txt_rv_item_product_list_brand)
        val txt_rv_item_product_list_name: TextView = itemView.findViewById(R.id.txt_rv_item_product_list_name)
        val txt_rv_item_product_list_day_number: TextView = itemView.findViewById(R.id.txt_rv_item_product_list_day_number)
        val txt_rv_item_product_list_cost_number: TextView = itemView.findViewById(R.id.txt_rv_item_product_list_cost_number)
        val txt_rv_item_product_list_price_number: TextView = itemView.findViewById(R.id.txt_rv_item_product_list_price_number)
        val img_rv_item_product_list: ImageView = itemView.findViewById(R.id.img_rv_item_product_list)

        val rv_rv_item_product_list: RecyclerView = itemView.findViewById(R.id.rv_rv_item_product_list)

        val productIngredientRvAdapter = ProductIngredientRvAdapter()


        fun bind(item: ProductInfo) {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                context.startActivity(intent)
            }
            txt_rv_item_product_list_brand.text = item.brandName
            txt_rv_item_product_list_name.text = item.productName
            txt_rv_item_product_list_day_number.text = item.maintainDays.toString()
            txt_rv_item_product_list_cost_number.text = item.lowPrice.toString()
            txt_rv_item_product_list_price_number.text = item.monthlyPrice.toString()
            productIngredientRvAdapter.items.add(item.criterion1)
            productIngredientRvAdapter.items.add(item.criterion2)
            productIngredientRvAdapter.items.add(item.criterion3)
            Glide.with(itemView.context).load(item.imgUrl).into(img_rv_item_product_list)

            rv_rv_item_product_list.adapter = productIngredientRvAdapter

        }

    }
}
data class ProductListData(
    val brand: String,
    val name: String,
    val maintenance: String,
    val price: String,
    val maintenancePrice: String,
    val Ingredient: MutableList<String>

)