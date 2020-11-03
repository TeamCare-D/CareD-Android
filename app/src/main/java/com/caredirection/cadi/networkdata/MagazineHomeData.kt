package com.caredirection.cadi.networkdata

data class MagazineHome(
    val status: Int,
    val message: String,
    val data: MagazineHomeData
)

data class MagazineHomeData(
    val directions: MutableList<MagazineDirections>,
    val magazine: MutableList<MagazineListData>
)
data class MagazineDirections(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String,
    val hashtag_name: MutableList<String>
)