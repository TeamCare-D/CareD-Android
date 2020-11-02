package com.caredirection.cadi.register.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvTakeSearchItem

class RegisterSearchViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

    private val txtProductBrand : TextView = view.findViewById(R.id.txt_register_search_result_brand)
    private val txtProductName : TextView = view.findViewById(R.id.txt_register_search_result_name)
    private val txtProductOverseas : TextView = view.findViewById(R.id.txt_register_search_result_overseas)
    private val txtProductDay : TextView = view.findViewById(R.id.txt_register_search_result_day)
    private val imgProductUrl : ImageView = view.findViewById(R.id.img_register_search_result)

    fun onBind(product: RvTakeSearchItem){
        txtProductBrand.text = product.brand
        txtProductName.text = product.name
        txtProductDay.text = product.day.toString()

        if(product.overseas == 1){
            txtProductOverseas.text = "해외직구"
        }

        Glide.with(view)
            .load(product.imgUrl)
            .centerCrop()
            .into(imgProductUrl)
    }
}