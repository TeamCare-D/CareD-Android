package com.caredirection.cadi.networkdata

data class GraphBitaminList(
    val status: Int,
    val message: String,
    val data: List<GraphBitamin>
)

data class GraphBitamin(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_percentage: Int
)