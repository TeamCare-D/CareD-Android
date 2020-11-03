package com.caredirection.cadi.mypage.interest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.RvMypageInterestListItem

class MypageInterestProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

    private val txtProductBrand : TextView = view.findViewById(R.id.txt_mypage_interest_brand)
    private val txtProductName : TextView = view.findViewById(R.id.txt_mypage_interest_name)
    private val txtProductOverseas : TextView = view.findViewById(R.id.txt_mypage_interest_overseas)
    private val txtProductDay : TextView = view.findViewById(R.id.txt_mypage_interest_day)
    private val imgProductUrl : ImageView = view.findViewById(R.id.img_mypage_interest_product)

    fun onBind(product: RvMypageInterestListItem){
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