package com.caredirection.cadi.research.medicine

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchMedicineViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val btnMedicine : CheckBox = view.findViewById(R.id.btn_item)

    fun onBind(research: RvResearchListItem){
        btnMedicine.text = research.item

//        btnDisease.setOnClickListener {
//            btnDisease.isChecked = !btnDisease.isChecked
//        }
    }
}