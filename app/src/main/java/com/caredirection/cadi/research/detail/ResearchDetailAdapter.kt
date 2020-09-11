package com.caredirection.cadi.research.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchDetailAdapter(private val context: Context) : RecyclerView.Adapter<ResearchDetailViewHolder>(){

    var data : List<RvResearchListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnDisease : CheckedTextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchDetailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        btnDisease = view.findViewById(R.id.btn_item)

        return ResearchDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResearchDetailViewHolder, position: Int) {
        holder.onBind(data[position])

        btnDisease.setOnClickListener {
            if(selectedItem.contains(data[position].itemIdx)){
                selectedItem.remove(data[position].itemIdx)
                btnDisease.isChecked = !btnDisease.isChecked
                //Log.d("명",data[position].productIdx.toString()+"삭제")
            }
            else{
                selectedItem.add(data[position].itemIdx)
                btnDisease.isChecked = !btnDisease.isChecked
                //Log.d("명",data[position].productIdx.toString()+"추가")
            }
            (context as ResearchDiseaseActivity).checkNextButton()
        }
    }
}