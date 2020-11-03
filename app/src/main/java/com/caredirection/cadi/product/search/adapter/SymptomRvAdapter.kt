package com.caredirection.cadi.product.search.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import java.lang.Exception


class SymptomRvAdapter(private val context: Context) : RecyclerView.Adapter<SymptomRvAdapter.SymptomViewHolder>() {

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

        fun bind(item: item, position: Int) {

            val itemViewTxt: TextView = itemView.findViewById(R.id.txt_rv_item_product_search_symptom)
            val itemViewImg: ImageView = itemView.findViewById(R.id.img_rv_item_product_search_symptom)
            val rvViewStamina: RecyclerView = itemView.findViewById(R.id.rv_view_stamina)
            val itemViewLayout: View = itemView.findViewById(R.id.layout_rv_item_product_search_symptom)
            var viewRv: RecyclerView? = itemView.findViewById(R.id.rv_view_stamina)

            itemViewTxt.text = item.sysmptom


            itemViewLayout.setOnClickListener {
                if(!item.check){
                        var viewStaminaAdapter = SymptomIngredientRvAdapter(context, items[position].nameItem)
                        viewRv?.adapter = viewStaminaAdapter
                        viewRv?.layoutManager = GridLayoutManager(context, 3)
                        itemViewTxt.setTextColor(Color.parseColor("#358fff"))
                        val textFont = ResourcesCompat.getFont(context, R.font.notosanskr_bold)
                        itemViewTxt.typeface = textFont
                        itemView.setBackgroundColor(Color.parseColor("#f0f6fd"))

                        itemViewImg.setImageResource(R.drawable.btn_dropup_blue_home)
                        rvViewStamina.setPadding(0, 0, 0, 60)
                        item.check = true

                }
                else {
                    var viewStaminaAdapter = SymptomIngredientRvAdapter(context, mutableListOf())
                    viewRv?.adapter = viewStaminaAdapter
                    viewRv?.layoutManager = GridLayoutManager(context, 3)
                    val textFont = ResourcesCompat.getFont(context, R.font.notosanskr_regular)
                    rvViewStamina.setPadding(0, 0, 0, 0)
                    itemViewTxt.typeface = textFont

                    itemViewImg.setImageResource(R.drawable.btn_dropdown)
                    itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    itemViewTxt.setTextColor(Color.parseColor("#000000"))
                    item.check = false
                }

            }
        }

    }
}
data class item(
    var sysmptom: String,
    var nameItem: MutableList<ingredientIdxData>,
    var check: Boolean = false
)
data class ingredientIdxData(
    var nameItem: String,
    var ingredient_idx: Int
)