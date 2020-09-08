package com.caredirection.cadi.data.network

import com.google.gson.annotations.SerializedName

data class RegisterIngredientData(
    val status:Int,
    val message:String,
    val data: IngredientList
)

data class IngredientList(
    val vitaminMineralIngredients : List<RegisterIngredientItem>,
    val functionalIngredients : List<RegisterIngredientItem>
)

data class RegisterIngredientItem(
    @SerializedName("ingredient_idx")
    val ingredientIdx : Int,
    @SerializedName("ingredient_name")
    val name : String
)