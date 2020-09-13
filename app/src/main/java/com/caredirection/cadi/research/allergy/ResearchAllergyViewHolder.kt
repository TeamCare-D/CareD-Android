package com.caredirection.cadi.research.allergy

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchAllergyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val btnAllergy : CheckBox = view.findViewById(R.id.btn_item)

    fun onBind(research: RvResearchListItem){
        btnAllergy.text = research.item

//        btnDisease.setOnClickListener {
//            btnDisease.isChecked = !btnDisease.isChecked
//        }
    }
}