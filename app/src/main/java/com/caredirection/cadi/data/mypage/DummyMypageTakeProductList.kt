package com.caredirection.cadi.data.mypage

class DummyMypageTakeProductList {
    fun getMypageTakeProductList() : List<MypageTakeProductListItem>{
        return listOf(
            MypageTakeProductListItem(
                brand = "브랜드1",
                name = "상품명1",
                overseas = "해외직구",
                day = 30
            ),
            MypageTakeProductListItem(
                brand = "브랜드2",
                name = "상품명2",
                overseas = "해외직구",
                day = 50
            )
        )
    }
}