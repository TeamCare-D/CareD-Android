package com.caredirection.cadi.register.user.ingredient.vitamin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RegisterVitaminListItem

class RegisterVitaminAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterVitaminViewHolder>(){
    var data: List<RegisterVitaminListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterVitaminViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_vitamin, parent, false)

        return RegisterVitaminViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterVitaminViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}