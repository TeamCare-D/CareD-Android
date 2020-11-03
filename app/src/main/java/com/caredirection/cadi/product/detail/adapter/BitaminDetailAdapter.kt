package com.caredirection.cadi.product.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.ProductDetailGraph
import com.caredirection.cadi.networkdata.ProductFunctionalGraphData

class BitaminDetailAdapter(val context: Context): RecyclerView.Adapter<BitaminDetailAdapter.BitaminDetailHolder>(){

    val items = mutableListOf<ProductDetailGraph>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitaminDetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_detail_ingredient, parent, false)
        return BitaminDetailHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BitaminDetailHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class BitaminDetailHolder(view: View): RecyclerView.ViewHolder(view){
        val rv_rv_item_detail_ingredient: RecyclerView = itemView.findViewById(R.id.rv_rv_item_detail_ingredient)
        val rvCategoryAdapter = RvCategoryAdapter(context)
        val txt_rv_item_detail_ingredient_day_content: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_day_content2)
        val txt_rv_item_detail_ingredient_prediction: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_prediction)

        val txt_rv_item_detail_ingredient_increase: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_increase)
        val txt_rv_item_detail_ingredient_day_content1: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_day_content1)
        val txt_rv_item_detail_ingredient_day_content2: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_day_content2)
        val txt_rv_item_detail_ingredient_under: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_under)
        val txt_rv_item_detail_ingredient: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient)



        fun bind(item: ProductDetailGraph){
            rvCategoryAdapter.items.addAll(item.ingredient_sub_name)
            rv_rv_item_detail_ingredient.adapter = rvCategoryAdapter

            txt_rv_item_detail_ingredient.text = item.ingredient_name
            txt_rv_item_detail_ingredient_day_content1.text = item.upper_amount
            txt_rv_item_detail_ingredient_day_content2.text = item.recommended_amount
            txt_rv_item_detail_ingredient_prediction.text = item.product_ingredient_value
            txt_rv_item_detail_ingredient_under.text = item.isExpectedAmountProper
            txt_rv_item_detail_ingredient_increase.text = item.product_increase_value.toString()
            txt_rv_item_detail_ingredient_day_content.text = item.upper_amount
            txt_rv_item_detail_ingredient_prediction.text = item.expected_intake_value
        }
    }
}