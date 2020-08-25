package com.caredirection.cadi.product.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class RvIngredientAdapter(val context: Context): RecyclerView.Adapter<RvIngredientAdapter.RvIngredientHoler>(){

    val items = mutableListOf<RvIngredientData>()

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

        fun bind(text: RvIngredientData){
            rvCategoryAdapter.items.addAll(text.category)
            rv_rv_item_detail_ingredient.adapter = rvCategoryAdapter
            rv_rv_item_detail_ingredient.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        }
    }
}

data class RvIngredientData(
    var name: String,
    var contentDay: String,
    var predictionContent: String,
    var contentIncrease: String,
    var category: MutableList<String>
)