package com.caredirection.cadi.data.network

data class ResearchSelecListData(
    val age : Int,
    val allergy : List<Int>,
    val diseaseMedicine : List<Int>,
    val efficacy : List<Int>,
    val gender : Int,
    val nickName : String,
    val warning : List<Int>
)

