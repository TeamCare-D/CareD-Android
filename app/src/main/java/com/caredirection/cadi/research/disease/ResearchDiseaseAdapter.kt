package com.caredirection.cadi.research.disease

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchDiseaseAdapter(private val context: Context) : RecyclerView.Adapter<ResearchDiseaseViewHolder>(){

    var data : List<RvResearchListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnDisease : CheckBox

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

        btnDisease.isChecked = false

        btnDisease.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun isItemSelected(position: Int): Boolean{
        return selectedItem.contains(data[position].itemIdx)
    }

    private fun toggleItemSelected(position: Int) {
        if (selectedItem.contains(data[position].itemIdx)) {
            selectedItem.remove(data[position].itemIdx)
            Log.d("명",data[position].itemIdx.toString()+"삭제")
        } else {
            if(position==0){
                selectedItem.forEach { index ->
                    notifyItemChanged(index)
                }
                selectedItem.clear()
                selectedItem.add(0)
            }
            else{
                selectedItem.add(data[position].itemIdx)
                selectedItem.remove(0)
            }
            Log.d("명",data[position].itemIdx.toString()+"추가")
        }
        Log.d("명",selectedItem.toString())
        (context as ResearchDiseaseActivity).checkNextButton()
    }
}