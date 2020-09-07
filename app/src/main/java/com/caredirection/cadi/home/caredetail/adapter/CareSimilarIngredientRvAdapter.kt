package com.caredirection.cadi.home.caredetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class CareSimilarIngredientRvAdapter: RecyclerView.Adapter<CareSimilarIngredientRvAdapter.CareSimilarIngredientRvHolder>() {

    var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareSimilarIngredientRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_rv_item_home_detail_similar, parent, false)
        return CareSimilarIngredientRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CareSimilarIngredientRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CareSimilarIngredientRvHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_rv_item_home_detail_similar_name: TextView = itemView.findViewById(R.id.txt_rv_rv_item_home_detail_similar_name)

        fun bind(item: String){
            txt_rv_rv_item_home_detail_similar_name.text = item
        }
    }

}