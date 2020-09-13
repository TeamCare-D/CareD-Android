package com.caredirection.cadi.research.interest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchInterestViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val btnInterest : TextView = view.findViewById(R.id.btn_item)

    fun onBind(detail: RvResearchListItem){
        btnInterest.text = detail.item

//        btnDisease.setOnClickListener {
//            btnDisease.isChecked = !btnDisease.isChecked
//        }
    }
}