package com.caredirection.cadi.register.user

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvSelectListItem

class RegisterSelectViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val txtIngredientName : TextView = view.findViewById(R.id.txt_register_ingredient_name)
    private val edtIngredientContent : EditText = view.findViewById(R.id.edt_register_ingredient_content)
    private val txtIngredientUnit : TextView = view.findViewById(R.id.txt_register_ingredient_unit)
    private val btnIngredientUnit : ImageView = view.findViewById(R.id.btn_register_ingredient_unit)

    fun onBind(ingredient : RvSelectListItem){
        txtIngredientName.text = ingredient.name
        txtIngredientUnit.text = ingredient.unit[0].toString()

//        itemView.setOnClickListener {
//            btnVitaminCheck.isChecked = !btnVitaminCheck.isChecked
//        }
    }
}