package com.caredirection.cadi.home.caredetail.adapter

import android.graphics.Color
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.SimilarCareData

class CareSimliarRvAdapter : RecyclerView.Adapter<CareSimliarRvAdapter.CareSimliarRvHolder>() {

    var items = mutableListOf<SimilarCareData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareSimliarRvHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_home_care_detail_similar, parent, false)
        return CareSimliarRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CareSimliarRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CareSimliarRvHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rv_rv_item_hoem_care_detail_similar: RecyclerView =
            itemView.findViewById(R.id.rv_rv_item_hoem_care_detail_similar)
        val careSimilarIngredientRvAdapter = CareSimilarIngredientRvAdapter()

        val txt_rv_item_home_care_detail_similar_sub_title: TextView =
            itemView.findViewById(R.id.txt_rv_item_home_care_detail_similar_sub_title)
        val img_rv_item_home_care_detail_similar_title: ImageView =
            itemView.findViewById(R.id.img_rv_item_home_care_detail_similar_title)

        val line_rv_item_home_care_detail_similar: TextView =
            itemView.findViewById(R.id.line_rv_item_home_care_detail_similar)

        val txt_rv_item_home_care_detail_similar_title_name: TextView =
            itemView.findViewById(R.id.txt_rv_item_home_care_detail_similar_title_name)
        val faceTrue = ResourcesCompat.getFont(itemView.context, R.font.notosanskr_bold)
        val faceFalse = ResourcesCompat.getFont(itemView.context, R.font.notosanskr_regular)

        var checked: Boolean = false

        fun bind(item: SimilarCareData) {
            careSimilarIngredientRvAdapter.items.addAll(item.ingredientName)
            rv_rv_item_hoem_care_detail_similar.adapter = careSimilarIngredientRvAdapter

            line_rv_item_home_care_detail_similar.visibility = View.GONE



            itemView.setOnClickListener {
                if (!checked) {
                    rv_rv_item_hoem_care_detail_similar.visibility = View.VISIBLE
                    txt_rv_item_home_care_detail_similar_sub_title.visibility = View.VISIBLE
                    line_rv_item_home_care_detail_similar.visibility = View.VISIBLE

                    img_rv_item_home_care_detail_similar_title.setImageResource(R.drawable.btn_dropup_red_home)
                    txt_rv_item_home_care_detail_similar_title_name.typeface = faceTrue

                    itemView.setBackgroundColor(Color.parseColor("#ffefee"))

                    checked = true
                } else {
                    rv_rv_item_hoem_care_detail_similar.visibility = View.GONE
                    txt_rv_item_home_care_detail_similar_sub_title.visibility = View.GONE
                    line_rv_item_home_care_detail_similar.visibility = View.GONE
                    img_rv_item_home_care_detail_similar_title.setImageResource(R.drawable.btn_dropdown)
                    txt_rv_item_home_care_detail_similar_title_name.typeface = faceFalse

                    itemView.setBackgroundColor(Color.parseColor("#ffffff"))
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