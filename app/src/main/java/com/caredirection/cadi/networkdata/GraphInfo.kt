package com.caredirection.cadi.networkdata

data class GraphInfo(
    val status: Int,
    val message: String,
    val data: List<GgraphInfoList>
)

data class GgraphInfoList(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_percentage:Int
)