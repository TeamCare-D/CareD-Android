package com.caredirection.cadi.research.allergy

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchAllergyAdapter(private val context: Context) : RecyclerView.Adapter<ResearchAllergyViewHolder>(){

    var data : List<RvResearchListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnAllergy : CheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchAllergyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        btnAllergy = view.findViewById(R.id.btn_item)

        return ResearchAllergyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResearchAllergyViewHolder, position: Int) {
        holder.onBind(data[position])

        btnAllergy.setOnClickListener {
            toggleItemSelected(position)
        }
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
        (context as ResearchAllergyActivity).checkNextButton()
    }
}