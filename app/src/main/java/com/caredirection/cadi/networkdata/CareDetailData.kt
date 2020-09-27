package com.caredirection.cadi.networkdata

data class CareDetailData(
    val status: Int,
    val message: String,
    val data: CareDetailDataList
)

data class CareDetailDataList(
    val userCaredEfficacyList: MutableList<UserCaredEfficacyListData>,
    val SimilarCare: MutableList<SimilarCareData>
)
data class UserCaredEfficacyListData(
    val efficacyName: String,
    val totalCount: Int,
    val vitaminMineralCount: Int,
    val vitaminMineralGraph: MutableList<GraphBitamin>,
    val functionalCount: Int,
    val functionalGraph: MutableList<GraphBitamin>
)
data class VitaminMineralGraphData(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_percentage: Int
)

data class SimilarCareData(
    val efficacyName: String,
    val ingredientName: MutableList<String>
)