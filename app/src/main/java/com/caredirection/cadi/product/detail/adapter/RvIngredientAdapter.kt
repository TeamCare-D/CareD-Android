package com.caredirection.cadi.product.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.ProductDetailGraph
import com.caredirection.cadi.networkdata.ProductFunctionalGraphData

class RvIngredientAdapter(val context: Context): RecyclerView.Adapter<RvIngredientAdapter.RvIngredientHoler>(){

    val items = mutableListOf<ProductFunctionalGraphData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvIngredientHoler {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_detail_ingredient, parent, false)
        return RvIngredientHoler(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RvIngredientHoler, position: Int) {
        holder.bind(items[position])
    }

    inner class RvIngredientHoler(view: View): RecyclerView.ViewHolder(view){
        val rv_rv_item_detail_ingredient: RecyclerView = itemView.findViewById(R.id.rv_rv_item_detail_ingredient)
        val rvCategoryAdapter = RvCategoryAdapter(context)
        val txt_rv_item_detail_ingredient_day_content: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_day_content)
        val txt_rv_item_detail_ingredient_prediction: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_prediction)
        val txt_rv_item_detail_ingredient_under: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_under)
        val txt_rv_item_detail_ingredient_increase: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_increase)


        fun bind(item: ProductFunctionalGraphData){
            rvCategoryAdapter.items.addAll(item.ingredient_sub_name)
            rv_rv_item_detail_ingredient.adapter = rvCategoryAdapter
            rv_rv_item_detail_ingredient.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            txt_rv_item_detail_ingredient_day_content.text = item.upper_amount
            txt_rv_item_detail_ingredient_prediction.text = item.expected_intake_value
        }
    }
}