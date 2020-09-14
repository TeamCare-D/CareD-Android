package com.caredirection.cadi.networkdata

data class ProductSearchData(
    val status: Int,
    val message: String,
    val data: ProductSearchInfoList
)

data class ProductSearchInfoList(
    val criterion: ProductSearchInfo,
    val products: List<ProductInfo>
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
    val criterionDescription1: criterionDescription1Data,
    val criterionDescription2: criterionDescription2Data,
    val criterionDescription3: criterionDescription3Data
)

data class ProductInfo(
    val productIdx: Int,
    val productName: String,
    val imgUrl: String,
    val isImport: Int,
    val brandName: String,
    val criterion1: String,
    val criterion2: String,
    val criterion3: String,
    val lowPrice: Int,
    val monthlyPrice: Int,
    val maintainDays: Int
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
