package com.caredirection.cadi.networkdata

data class MagazineDirectionData(
    val status: Int,
    val message: String,
    val data: List<MagazineDirectionDataList>
)
data class MagazineDirectionDataList(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String,
    val hashtag_name: List<String>
)