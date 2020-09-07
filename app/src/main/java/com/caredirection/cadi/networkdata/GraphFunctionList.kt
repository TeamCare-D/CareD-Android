package com.caredirection.cadi.networkdata

data class GraphFunctionList(
    val status: Int,
    val message: String,
    val data: List<GraphFunction>
)

data class GraphFunction(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_percentage:Int
)