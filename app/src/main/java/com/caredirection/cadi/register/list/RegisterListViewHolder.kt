package com.caredirection.cadi.register.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvTakeListItem

class RegisterListViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val txtProductBrand : TextView = view.findViewById(R.id.txt_register_list_brand)
    private val txtProductName : TextView = view.findViewById(R.id.txt_register_list_name)
    private val txtProductOverseas : TextView = view.findViewById(R.id.txt_register_list_overseas)
    private val txtProductDay : TextView = view.findViewById(R.id.txt_register_list_day)

    fun onBind(product: RvTakeListItem){
        txtProductBrand.text = product.brand
        txtProductName.text = product.name
        txtProductDay.text = product.day.toString()

        if(product.overseas == 1){
            txtProductOverseas.text = "해외직구"
        }

//        Glide.with(this@MypageInterestProductActivity)
//            .load(detailsReso.main_contents.image_key)
//            .centerCrop()
//            .into(img_article_details)
    }
}