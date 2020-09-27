package com.caredirection.cadi.networkdata

data class IngredientDetail(
    val status: Int,
    val message: String,
    val data: IngredientData
)

data class IngredientData(
    val graphVitaminMineralDetail: GraphVitaminMineralDetailData,
    val magazineList: MutableList<MagazineListData>
)
data class GraphVitaminMineralDetailData(
    val ingredient_name: String,
    val vitamin_mineral_recommended_amount: String,
    val vitamin_mineral_upper_amount: String,
    val ingredient_description: String,
    val my_amount: String,
    val ingredient_unit: String
)
data class MagazineListData(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String,
    val hashtag_name: MutableList<String>
)