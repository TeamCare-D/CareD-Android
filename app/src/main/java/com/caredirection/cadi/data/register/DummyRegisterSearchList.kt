package com.caredirection.cadi.data.register

class DummyRegisterSearchList {
    fun getRegisterSearchResultList() : List<RegisterSearchListItem>{
        return listOf(
            RegisterSearchListItem(
                brand = "브랜드1",
                name = "상품명1",
                overseas = "해외직구",
                day = 30
            ),
            RegisterSearchListItem(
                brand = "브랜드2",
                name = "상품명2",
                overseas = "국내제품",
                day = 50
            )
            ,
            RegisterSearchListItem(
                brand = "브랜드3",
                name = "상품명3",
                overseas = "국내제품",
                day = 40
            )
            ,
            RegisterSearchListItem(
                brand = "브랜드4",
                name = "상품명4",
                overseas = "해외직구",
                day = 60
            )
        )
    }
}