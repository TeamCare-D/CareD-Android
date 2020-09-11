package com.caredirection.cadi.register.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvSelectListItem

class RegisterSelectAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterSelectViewHolder>(){
    var data: List<RvSelectListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterSelectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_product_ingredient, parent, false)

        return RegisterSelectViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterSelectViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}