package com.example.edamantestapp.data.remoteData.entities

import kotlinx.serialization.Serializable

@Serializable
data class DailyItem(
    val label:String?,
    val quantity:Float?,
    val unit:String?,
)
