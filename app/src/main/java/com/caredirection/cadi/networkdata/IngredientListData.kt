package com.caredirection.cadi.networkdata

data class IngredientListData(
    val status: Int,
    val message: String,
    val data: List<IngredientList>
)
data class IngredientList(
    val ingredient_idx: Int,
    val ingredient_name: String
)