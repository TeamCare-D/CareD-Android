package com.caredirection.cadi.networkdata

data class GraphIngredientList(
    val status: Int,
    val message: String,
    val data: List<GraphIngredient>
)

data class GraphIngredient(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_percentage: Int
)