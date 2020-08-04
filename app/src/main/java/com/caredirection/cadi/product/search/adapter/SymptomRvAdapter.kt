package com.caredirection.cadi.product.search.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R


class SymptomRvAdapter(private val context: Context) :
    RecyclerView.Adapter<SymptomRvAdapter.SymptomViewHolder>() {

    var items = mutableListOf<item>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {

            val view = LayoutInflater.from(context)
                .inflate(R.layout.rv_item_product_search_symptom, parent, false)
            return SymptomViewHolder(view, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class SymptomViewHolder(view: View, parent: ViewGroup) : RecyclerView.ViewHolder(view) {

        var viewRv: RecyclerView? = view.findViewById(R.id.rv_view_stamina)


        fun bind(item: item, position: Int) {

            val itemViewTxt: TextView = itemView.findViewById(R.id.txt_rv_item_product_search_symptom)
            val itemViewImg: ImageView = itemView.findViewById(R.id.img_rv_item_product_search_symptom)

            itemView.setOnClickListener {
                if(!item.check){
                    var viewStaminaAdapter = testRvAdapter(context, items[position].nameItem)
                    viewRv?.adapter = viewStaminaAdapter
                    viewRv?.layoutManager = GridLayoutManager(context, 3)

                    itemViewTxt.setTextColor(Color.parseColor("#358fff"))
                    //itemViewImg.animate().rotation(0.0F).start()
                    item.check = true
                    notifyDataSetChanged()
                }
                else {
                    var viewStaminaAdapter = testRvAdapter(context, mutableListOf())
                    viewRv?.adapter = viewStaminaAdapter
                    viewRv?.layoutManager = GridLayoutManager(context, 3)

                    itemViewTxt.setTextColor(Color.parseColor("#000000"))
                    //itemViewImg.animate().rotation(0.0F).start()
                    item.check = false
                }
            }

        }

    }
}
data class item(
    var sysmptom: String,
    var nameItem: MutableList<String>,
    var check: Boolean = false
)
