package com.example.edamantestapp.data.remoteData.entities

import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort
import kotlinx.serialization.Serializable

@Serializable
data class RecipeItem(
    val uri:String?=null,
    val label:String?=null,
    val image:String?=null,
    val images:Images?=null,
    val source:String?=null,
    val url:String?=null,
    val shareAs:String?=null,
    val yield:Double?=null,
    val dietLabels:List<String>?=null,
    val healthLabels:List<String>?=null,
    val cautions:List<String>?=null,
    val ingredientLines:List<String>?=null,
    val ingredients:List<IngredientItem>?=null,
    val calories:Float?=null,
    val totalCO2Emissions:Float?=null,
    val co2EmissionsClass:String?=null,
    val totalWeight:Float?=null,
    val totalTime:Double?=null,
    val cuisineType:List<String>?,
    val mealType:List<String>?=null,
    val dishType:List<String>?=null,
    val totalNutrients:TotalNutrient?=null,
    val totalDaily:TotalNutrient?=null,
    val digest:List<DigestItem>?=null
){
    fun getKcal():Int{
        val cal  = calories ?: (1.0f)
        val weight = totalWeight?:1
        return ((cal.toInt()/weight.toInt())*100)
    }

    fun getRecipeShort() = RecipeItemShort(
        uri?:"",
        label,
        images?.regular?.url,
        images?.small?.url,
        calories?.toInt(),
        totalTime,
        ingredients?: emptyList()
    )

}
