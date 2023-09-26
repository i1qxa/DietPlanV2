package com.example.edamantestapp.data.remoteData.entities

import kotlinx.serialization.Serializable

@Serializable
data class Next(
    val href:String?,
    val title:String?,
)
