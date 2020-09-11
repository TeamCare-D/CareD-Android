package com.caredirection.cadi.research.detail

import android.view.View
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchDetailViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val btnDisease : CheckedTextView = view.findViewById(R.id.btn_item)

    fun onBind(detail: RvResearchListItem){
        btnDisease.text = detail.item

//        btnDisease.setOnClickListener {
//            btnDisease.isChecked = !btnDisease.isChecked
//        }
    }
}