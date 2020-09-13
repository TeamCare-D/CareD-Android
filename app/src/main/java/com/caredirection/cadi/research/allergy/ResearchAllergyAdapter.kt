package com.caredirection.cadi.research.allergy

import android.content.Context
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
            if(selectedItem.contains(data[position].itemIdx)){
                selectedItem.remove(data[position].itemIdx)
                btnAllergy.isChecked = !btnAllergy.isChecked
                //Log.d("명",data[position].productIdx.toString()+"삭제")
            }
            else{
                selectedItem.add(data[position].itemIdx)
                btnAllergy.isChecked = !btnAllergy.isChecked
                //Log.d("명",data[position].productIdx.toString()+"추가")
            }
            (context as ResearchAllergyActivity).checkNextButton()
        }
    }
}