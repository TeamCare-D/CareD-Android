package com.caredirection.cadi.data.network

data class ResearchTokenData(
    val status:Int,
    val message:String,
    val data: TokenItem
)

data class TokenItem(
    val token : String
)