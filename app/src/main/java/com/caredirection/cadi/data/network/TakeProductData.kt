package com.caredirection.cadi.data.network

import com.google.gson.annotations.SerializedName

data class TakeProductData(
    val status:Int,
    val message:String,
    val data: TakeList
)

data class TakeList(
    val products: List<TakeProductItem>
)

data class TakeProductItem(
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