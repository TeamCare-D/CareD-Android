package com.caredirection.cadi.data.register

class DummyRegisterList {
    fun getRegisterList() : MutableList<RegisterListItem>{
        return mutableListOf(
            RegisterListItem(
                brand = "브랜드1",
                name = "상품명1",
                overseas = "해외직구",
                day = 30
            ),
            RegisterListItem(
                brand = "브랜드2",
                name = "상품명2",
                overseas = "국내제품",
                day = 50
            )
        )
    }
}