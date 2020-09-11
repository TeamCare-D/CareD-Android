package com.caredirection.cadi.data.network

import com.google.gson.annotations.SerializedName

data class ResearchItemData(
    val status:Int,
    val message:String,
    val data: ResearchItemList
)

data class ResearchItemList(
    val userEfficacy : List<EfficacyItem>,
    val userWarning : List<WarningItem>,
    val userDiseaseMedicine : List<DiseaseItem>,
    val userAllergy: List<AllergyItem>
)

data class EfficacyItem(
    @SerializedName("efficacy_idx")
    val itemIdx : Int,
    @SerializedName("efficacy_name")
    val name : String
)

data class WarningItem(
    @SerializedName("warning_idx")
    val itemIdx : Int,
    @SerializedName("warning_name")
    val name : String
)

data class DiseaseItem(
    @SerializedName("disease_medicine_idx")
    val itemIdx : Int,
    @SerializedName("disease_medicine_name")
    val name : String
)

data class AllergyItem(
    @SerializedName("allergy_idx")
    val itemIdx : Int,
    @SerializedName("allergy_name")
    val name : String
)