package com.caredirection.cadi.research.interest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchInterestAdapter(private val context: Context) : RecyclerView.Adapter<ResearchInterestViewHolder>(){

    var data : List<RvResearchListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnInterest : CheckBox

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

        btnInterest.setOnClickListener {
            if(selectedItem.contains(data[position].itemIdx)){
                selectedItem.remove(data[position].itemIdx)
                btnInterest.isChecked = !btnInterest.isChecked
                //Log.d("명",data[position].productIdx.toString()+"삭제")
            }
            else{
                selectedItem.add(data[position].itemIdx)
                btnInterest.isChecked = !btnInterest.isChecked
                //Log.d("명",data[position].productIdx.toString()+"추가")
            }
            (context as ResearchInterestActivity).checkNextButton()
        }
    }
}