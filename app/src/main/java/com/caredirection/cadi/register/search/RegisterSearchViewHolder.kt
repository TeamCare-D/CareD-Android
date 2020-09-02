package com.caredirection.cadi.register.search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RegisterSearchListItem

class RegisterSearchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val txtProductBrand : TextView = view.findViewById(R.id.txt_register_search_result_brand)
    private val txtProductName : TextView = view.findViewById(R.id.txt_register_search_result_name)
    private val txtProductOverseas : TextView = view.findViewById(R.id.txt_register_search_result_overseas)
    private val txtProductDay : TextView = view.findViewById(R.id.txt_register_search_result_day)

    fun onBind(product: RegisterSearchListItem){
        txtProductBrand.text = product.brand
        txtProductName.text = product.name
        txtProductOverseas.text = product.overseas
        txtProductDay.text = product.day.toString()
    }
}