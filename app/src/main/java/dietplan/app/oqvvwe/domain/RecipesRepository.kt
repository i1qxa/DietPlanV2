package dietplan.app.oqvvwe.domain

import androidx.lifecycle.LiveData
import dietplan.app.oqvvwe.data.db.RecipeDB
import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort

interface RecipesRepository {

    fun getRecipeList():LiveData<List<RecipeDB>>

    suspend fun insertRecipe(recipe:RecipeDB)

    suspend fun clearPlan()

    suspend fun deleteRecipe(recipe:RecipeDB)

}