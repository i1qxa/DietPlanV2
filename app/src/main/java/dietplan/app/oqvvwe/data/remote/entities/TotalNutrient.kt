package com.example.edamantestapp.data.remoteData.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalNutrient(
    @SerialName("ENERC_KCAL")
    val enercKcal:NutrientItem?=null,
    @SerialName("FAT")
    val fat:NutrientItem?=null,
    @SerialName("FASAT")
    val fasat:NutrientItem?=null,
    @SerialName("FATRN")
    val fatrn:NutrientItem?=null,
    @SerialName("FAMS")
    val fams:NutrientItem?=null,
    @SerialName("FAPU")
    val fapu:NutrientItem?=null,
    @SerialName("CHOCDF")
    val chocdf:NutrientItem?=null,
    @SerialName("CHOCDF.net")
    val chocdfNet:NutrientItem?=null,
    @SerialName("FIBTG")
    val fibtg:NutrientItem?=null,
    @SerialName("SUGAR")
    val sugar:NutrientItem?=null,
    @SerialName("SUGAR.added")
    val sugarAdded:NutrientItem?=null,
    @SerialName("PROCNT")
    val procnt:NutrientItem?=null,
    @SerialName("CHOLE")
    val chole:NutrientItem?=null,
    @SerialName("NA")
    val na:NutrientItem?=null,
    @SerialName("CA")
    val ca:NutrientItem?=null,
    @SerialName("MG")
    val mg:NutrientItem?=null,
    @SerialName("K")
    val k:NutrientItem?=null,
    @SerialName("FE")
    val FE:NutrientItem?=null,
    @SerialName("ZN")
    val zn:NutrientItem?=null,
    @SerialName("P")
    val p:NutrientItem?=null,
    @SerialName("VITA_RAE")
    val vitaRae:NutrientItem?=null,
    @SerialName("VITC")
    val vitc:NutrientItem?=null,
    @SerialName("THIA")
    val thia:NutrientItem?=null,
    @SerialName("RIBF")
    val ribf:NutrientItem?=null,
    @SerialName("NIA")
    val nia:NutrientItem?=null,
    @SerialName("VITB6A")
    val vitB:NutrientItem?=null,
    @SerialName("FOLDFE")
    val foldfe:NutrientItem?=null,
    @SerialName("FOLFD")
    val folfd:NutrientItem?=null,
    @SerialName("FOLAC")
    val folac:NutrientItem?=null,
    @SerialName("VITB12")
    val vitBTwelve:NutrientItem?=null,
    @SerialName("VITD")
    val vitd:NutrientItem?=null,
    @SerialName("TOCPHA")
    val tocpha:NutrientItem?=null,
    @SerialName("VITK1")
    val vitkOne:NutrientItem?=null,
    @SerialName("WATER")
    val water:NutrientItem?=null,
)
