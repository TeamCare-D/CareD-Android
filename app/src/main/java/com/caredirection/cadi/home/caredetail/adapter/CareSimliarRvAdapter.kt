package com.caredirection.cadi.home.caredetail.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class CareSimliarRvAdapter: RecyclerView.Adapter<CareSimliarRvAdapter.CareSimliarRvHolder>() {

    var items = mutableListOf<CareSimliarData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareSimliarRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_home_care_detail_similar, parent, false)
        return CareSimliarRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CareSimliarRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CareSimliarRvHolder(view: View): RecyclerView.ViewHolder(view){
        val rv_rv_item_hoem_care_detail_similar: RecyclerView = itemView.findViewById(R.id.rv_rv_item_hoem_care_detail_similar)
        val careSimilarIngredientRvAdapter = CareSimilarIngredientRvAdapter()

        val txt_rv_item_home_care_detail_similar_sub_title: TextView = itemView.findViewById(R.id.txt_rv_item_home_care_detail_similar_sub_title)
        val img_rv_item_home_care_detail_similar_title: ImageView = itemView.findViewById(R.id.img_rv_item_home_care_detail_similar_title)
        var checked: Boolean = false

        fun bind(item: CareSimliarData){
            careSimilarIngredientRvAdapter.items.addAll(item.IngredientList)
            rv_rv_item_hoem_care_detail_similar.adapter = careSimilarIngredientRvAdapter

            img_rv_item_home_care_detail_similar_title.setOnClickListener{
                if(!checked){
                    rv_rv_item_hoem_care_detail_similar.visibility = View.VISIBLE
                    txt_rv_item_home_care_detail_similar_sub_title.visibility = View.VISIBLE
                    checked = true
                }
                else{
                    rv_rv_item_hoem_care_detail_similar.visibility = View.GONE
                    txt_rv_item_home_care_detail_similar_sub_title.visibility = View.GONE
                    checked = false
                }

            }

        }
    }
}
data class CareSimliarData(
    var name: String,
    var IngredientList: MutableList<String>
)