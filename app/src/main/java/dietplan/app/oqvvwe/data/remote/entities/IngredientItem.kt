package com.example.edamantestapp.data.remoteData.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class IngredientItem(
    val text: String?,
    val quantity: Double?,
    val measure: String? = null,
    val food: String?,
    val weight: Double?,
    val foodCategory: String?,
    val foodId: String?,
    val image: String?,
):Parcelable
