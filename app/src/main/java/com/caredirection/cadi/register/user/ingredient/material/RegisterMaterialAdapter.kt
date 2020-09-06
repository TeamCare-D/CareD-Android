package com.caredirection.cadi.register.user.ingredient.material

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvIngredientListItem

class RegisterMaterialAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterMaterialViewHolder>(){
    var data: List<RvIngredientListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterMaterialViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_material,parent,false)

        return RegisterMaterialViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterMaterialViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}