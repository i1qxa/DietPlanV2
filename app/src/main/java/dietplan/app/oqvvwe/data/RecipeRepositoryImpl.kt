package dietplan.app.oqvvwe.data

import android.app.Application
import androidx.lifecycle.LiveData
import dietplan.app.oqvvwe.data.db.AppDatabase
import dietplan.app.oqvvwe.data.db.RecipeDB
import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort
import dietplan.app.oqvvwe.domain.RecipesRepository

class RecipeRepositoryImpl(application: Application):RecipesRepository {

    private val dao = AppDatabase.getInstance(application).recipeDao()

    override fun getRecipeList(): LiveData<List<RecipeDB>> {
        return dao.getRecipesList()
    }

    override suspend fun insertRecipe(recipe: RecipeDB) {
        dao.insertRecipe(recipe)
    }

    override suspend fun clearPlan() {
        dao.clearRecipe()
    }

    override suspend fun deleteRecipe(recipe: RecipeDB) {
        dao.deleteRecipe(recipe)
    }
}