package com.example.edamantestapp.data.remoteData.entities

import kotlinx.serialization.Serializable

@Serializable
data class NutrientItem(
    val label:String?,
    val quantity:Double?,
    val unit:String?,
)
