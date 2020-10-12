package com.caredirection.cadi.networkdata

data class EfficacyListData(
    val status: Int,
    val message: String,
    val data: MutableList<List<EfficacyData>>
)

data class EfficacyData(
    val ingredient_idx: Int,
    val efficacy_name: String,
    val ingredient_name: String
)