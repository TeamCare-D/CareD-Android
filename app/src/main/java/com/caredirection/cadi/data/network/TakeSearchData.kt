package com.caredirection.cadi.data.network

import com.google.gson.annotations.SerializedName

data class TakeSearchData(
    val status:Int,
    val message:String,
    val data: TakeSearchList
)

data class TakeSearchList(
    val products: List<TakeProductItem>
)

data class TakeSearchItem(
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