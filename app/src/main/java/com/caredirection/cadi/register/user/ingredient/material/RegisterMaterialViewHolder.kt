package com.caredirection.cadi.register.user.ingredient.material

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvIngredientListItem

class RegisterMaterialViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val txtMaterialName : TextView = view.findViewById(R.id.txt_register_material_name)
    private val btnMaterialCheck : CheckBox = view.findViewById(R.id.btn_register_material_check)

    fun onBind(material : RvIngredientListItem){
        txtMaterialName.text = material.name

        itemView.setOnClickListener {
            btnMaterialCheck.isChecked = !btnMaterialCheck.isChecked
        }
    }
}