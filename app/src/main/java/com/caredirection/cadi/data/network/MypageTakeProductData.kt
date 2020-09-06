package com.caredirection.cadi.data.network

import com.google.gson.annotations.SerializedName

data class MypageTakeProductData(
    val status:Int,
    val message:String,
    val data: MypageTakeList
)

data class MypageTakeList(
    val products: List<MypageTakeProductItem>
)

data class MypageTakeProductItem(
    @SerializedName("productIdx")
    val productIdx : Int,
    @SerializedName("productName")
    val name : String,
    @SerializedName("imgUrl")
    val imgUrl : String,
    @SerializedName("isImport")
    val overseas : Int,
    @SerializedName("brandName")
    val brand : String,
    @SerializedName("maintainDays")
    val day : Int
)