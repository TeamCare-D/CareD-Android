package com.caredirection.cadi.research.disease

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem
import kotlinx.android.synthetic.main.rv_item_research.view.*

class ResearchDiseaseAdapter(private val context: Context) : RecyclerView.Adapter<ResearchDiseaseViewHolder>(){

    var data : List<RvResearchListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnDisease : TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchDiseaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        btnDisease = view.findViewById(R.id.btn_item)

        return ResearchDiseaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResearchDiseaseViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemBackground(holder, position)

        holder.itemView.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun setItemBackground(holder: ResearchDiseaseViewHolder, position: Int){
        if(isItemSelected(position)){
            holder.itemView.btn_item.background = context.resources.getDrawable(R.drawable.bluer_fill_4)
        }
        else{
            holder.itemView.btn_item.background = context.resources.getDrawable(R.drawable.gray_line_4)
        }
    }

    private fun isItemSelected(position: Int): Boolean{
        return selectedItem.contains(data[position].itemIdx)
    }

    private fun toggleItemSelected(position: Int) {
        if (selectedItem.contains(data[position].itemIdx)) {
            selectedItem.remove(data[position].itemIdx)
        }
        else {
            if(position==0){
                selectedItem.clear()
                selectedItem.add(0)
            }
            else{
                selectedItem.add(data[position].itemIdx)
                selectedItem.remove(0)
            }
        }
        notifyDataSetChanged()

        (context as ResearchDiseaseActivity).checkNextButton()
    }
}