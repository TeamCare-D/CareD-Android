package com.caredirection.cadi.data.register

class DummyRegisterSearchList {
    fun getRegisterSearchResultList() : List<RvTakeSearchItem>{
        return listOf(
            RvTakeSearchItem(
                productIdx = 0,
                imgUrl = "dd",
                brand = "브랜드1",
                name = "상품명1",
                overseas = 1,
                day = 30
            ),
            RvTakeSearchItem(
                productIdx = 1,
                imgUrl = "dd",
                brand = "브랜드2",
                name = "상품명2",
                overseas = 0,
                day = 50
            )
            ,
            RvTakeSearchItem(
                productIdx = 2,
                imgUrl = "dd",
                brand = "브랜드3",
                name = "상품명3",
                overseas = 1,
                day = 40
            )
            ,
            RvTakeSearchItem(
                productIdx = 3,
                imgUrl = "dd",
                brand = "브랜드4",
                name = "상품명4",
                overseas = 1,
                day = 60
            ),
            RvTakeSearchItem(
                productIdx = 4,
                imgUrl = "dd",
                brand = "브랜드2",
                name = "상품명2",
                overseas = 0,
                day = 50
            )
            ,
            RvTakeSearchItem(
                productIdx = 5,
                imgUrl = "dd",
                brand = "브랜드3",
                name = "상품명3",
                overseas = 0,
                day = 40
            )
            ,
            RvTakeSearchItem(
                productIdx = 6,
                imgUrl = "dd",
                brand = "브랜드4",
                name = "상품명4",
                overseas = 0,
                day = 60
            ),
            RvTakeSearchItem(
                productIdx = 7,
                imgUrl = "dd",
                brand = "브랜드2",
                name = "상품명2",
                overseas = 0,
                day = 50
            )
            ,
            RvTakeSearchItem(
                productIdx = 8,
                imgUrl = "dd",
                brand = "브랜드3",
                name = "상품명3",
                overseas = 1,
                day = 40
            )
            ,
            RvTakeSearchItem(
                productIdx = 9,
                imgUrl = "dd",
                brand = "브랜드4",
                name = "상품명4",
                overseas = 1,
                day = 60
            )
        )
    }
}