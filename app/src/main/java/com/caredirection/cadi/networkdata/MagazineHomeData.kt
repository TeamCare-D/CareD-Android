package com.caredirection.cadi.networkdata

data class MagazineHome(
    val status: Int,
    val message: String,
    val data: MagazineHomeData
)

data class MagazineHomeData(
    val directions: MutableList<MagazineDirections>,
    val magazine: MutableList<MagazineHomeList>
)
data class MagazineDirections(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String,
    val hashtag_name: MutableList<String>
)
data class MagazineHomeList(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String,
    val hashtag_name: MutableList<String>
)