package com.caredirection.cadi.mypage.interest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.MypageInterestProductListItem

class MypageInterestProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val txtProductBrand : TextView = view.findViewById(R.id.txt_mypage_interest_brand)
    private val txtProductName : TextView = view.findViewById(R.id.txt_mypage_interest_name)
    private val txtProductOverseas : TextView = view.findViewById(R.id.txt_mypage_interest_overseas)
    private val txtProductDay : TextView = view.findViewById(R.id.txt_mypage_interest_day)
    //private val btnDelete : Button = view.findViewById(R.id.btn_mypage_interest)

    fun onBind(product: MypageInterestProductListItem){
        txtProductBrand.text = product.brand
        txtProductName.text = product.name
        txtProductOverseas.text = product.overseas
        txtProductDay.text = product.day.toString()
    }
}