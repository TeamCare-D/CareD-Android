package com.caredirection.cadi.networkdata

data class MagazineGuideData(
    val status: Int,
    val message: String,
    val data: List<MagazineGuideDataList>
)
data class MagazineGuideDataList(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String
)