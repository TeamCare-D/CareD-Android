package com.caredirection.cadi.networkdata

data class ProductSearchData(
    val status: Int,
    val message: String,
    val data: ProductSearchInfoList
)

data class ProductSearchInfoList(
    val criterion: ProductSearchInfo
)
data class ProductSearchInfo(
    val ingredientName: String,
    val ingredientIdx: Int,
    val ingredientDescription: String,
    val criterionName1: String,
    val criterionValue1: List<String>,
    val criterionName2: String,
    val criterionValue2: List<String>,
    val criterionName3: String,
    val criterionValue3: List<String>,
    val criterionDescription1: List<criterionDescription1Data>,
    val criterionDescription2: List<criterionDescription2Data>,
    val criterionDescription3: List<criterionDescription3Data>,
    val products: List<String>
)

data class criterionDescription1Data(
    val subTitle: List<String>,
    val content: List<String>
)

data class criterionDescription2Data(
    val subTitle: List<String>,
    val content: List<String>
)

data class criterionDescription3Data(
    val subTitle: List<String>,
    val content: List<String>
)