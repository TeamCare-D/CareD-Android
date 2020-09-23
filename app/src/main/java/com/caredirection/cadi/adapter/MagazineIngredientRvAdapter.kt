package com.caredirection.cadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.MagazineList

class MagazineIngredientRvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<MagazineList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_magazine_ingredient, parent, false)
        return MagazineIngredientRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MagazineIngredientRvHolder){
            holder.bind(items[position])
        }
    }

    inner class MagazineIngredientRvHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_magazine_ingredient: TextView = view.findViewById(R.id.txt_rv_item_magazine_ingredient)
        val rv_rv_item_magazine_ingredient: RecyclerView = view.findViewById(R.id.rv_rv_item_magazine_ingredient)
        val magazineTagRvAdapter = MagazineTagRvAdapter()


        fun bind(item: MagazineList){
            txt_rv_item_magazine_ingredient.text = item.magazine_title
            magazineTagRvAdapter.items.addAll(item.hashtag_name)
            rv_rv_item_magazine_ingredient.adapter = magazineTagRvAdapter

        }
    }
}