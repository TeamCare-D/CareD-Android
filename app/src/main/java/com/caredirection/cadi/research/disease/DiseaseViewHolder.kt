package com.caredirection.cadi.research.disease

import android.view.View
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.DiseaseItem

class DiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val btnDisease : CheckedTextView = view.findViewById(R.id.btn_disease)

    fun onBind(item: DiseaseItem){
        btnDisease.text = item.disease

        itemView.setOnClickListener {
            btnDisease.isChecked = !btnDisease.isChecked
        }
    }
}

