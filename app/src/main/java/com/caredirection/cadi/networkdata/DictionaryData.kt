package com.caredirection.cadi.networkdata

data class DictionaryData(
    val status: Int,
    val message: String,
    val data: DictionaryDataList
)
data class DictionaryDataList(
    val dictionary: DictionaryListData,
    val magazine: MutableList<MagazineListData>
)
data class DictionaryListData(
    val ingredientName: String,
    val imgUrl: String,
    val ingredientSubTitle: String,
    val dictionaryContents: String,
    val recommendedAmount: String,
    val upperAmount: String,
    val efficacy: List<String>,
    val person: String
)