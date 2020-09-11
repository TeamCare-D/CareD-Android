package com.caredirection.cadi.networkdata

data class IngredientDetail(
    val status: Int,
    val message: String,
    val data: List<IngredientData>
)

data class IngredientData(
    val magazineList: MutableList<MagazineList>,
    val ingredient_name: String,
    val vitamin_mineral_recommended_amount: String,
    val vitamin_mineral_upper_amount: String,
    val ingredient_description: String,
    val my_amount: String,
    val ingredient_unit: String
)
