package com.example.edamantestapp.data.remoteData.entities

import kotlinx.serialization.Serializable

@Serializable
data class ImgItem(
    val url:String?,
    val width:Int?,
    val height:Int?,
)
