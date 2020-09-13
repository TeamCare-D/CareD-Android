package com.caredirection.cadi.research.disease

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchDiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val btnDisease : TextView = view.findViewById(R.id.btn_item)

    fun onBind(research: RvResearchListItem){
        btnDisease.text = research.item

//        btnDisease.setOnClickListener {
//            btnDisease.isChecked = !btnDisease.isChecked
//        }
    }
}