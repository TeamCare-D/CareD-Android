package com.caredirection.cadi.data.register

class DummySelectIngredient{
    fun getInitIngredient() : List<RvSelectListItem>{
        return listOf(
            RvSelectListItem(
                ingredientIdx = 0,
                name = "성분",
                unit = listOf(
                    "mg NE"
                )
            )
        )
    }
    fun getSelectIngredientList() : List<RvSelectListItem>{
        return listOf(
            RvSelectListItem(
                ingredientIdx = 0,
                name = "비타민B컴플렉스",
                unit = listOf(
                    "Mg",
                    "Mcg"
                )
            ),
            RvSelectListItem(
                ingredientIdx = 1,
                name = "루테인",
                unit = listOf(
                    "Mg",
                    "Mcg",
                    "IU"
                )
            ),
            RvSelectListItem(
                ingredientIdx = 2,
                name = "비타민A",
                unit = listOf(
                    "Mg"
                )
            )
        )
    }
}