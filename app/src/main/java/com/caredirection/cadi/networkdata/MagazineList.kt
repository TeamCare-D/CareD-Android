package com.caredirection.cadi.networkdata

data class MagazineList(
    val magazine_idx: Int,
    val magazine_title: String,
    val magazine_thumbnail_key: String,
    val hashtag_name: MutableList<String>
)
