package com.caredirection.cadi.research.interest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.ResearchSelectList
import com.caredirection.cadi.data.research.RvResearchListItem
import kotlinx.android.synthetic.main.rv_item_research.view.*

class ResearchInterestAdapter(private val context: Context) : RecyclerView.Adapter<ResearchInterestViewHolder>(){

    companion object{
        var selectedItem = ResearchSelectList.selectedInterestList
    }

    var data : List<RvResearchListItem> = listOf()
    private lateinit var btnInterest : TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchInterestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        btnInterest = view.findViewById(R.id.btn_item)

        return ResearchInterestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResearchInterestViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemBackground(holder, position)

        holder.itemView.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun setItemBackground(holder: ResearchInterestViewHolder, position: Int){
        if(isItemSelected(position)){
            holder.itemView.btn_item.background = context.resources.getDrawable(R.drawable.bluer_fill_4)
        }
        else{
            holder.itemView.btn_item.background = context.resources.getDrawable(R.drawable.gray_line_4)
        }

        (context as ResearchInterestActivity).checkNextButton()
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
    }
}