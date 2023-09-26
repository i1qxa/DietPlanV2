package dietplan.app.oqvvwe.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edamantestapp.data.remoteData.entities.Hit
import com.example.edamantestapp.data.remoteData.entities.RecipeItem
import dietplan.app.oqvvwe.data.db.AppDatabase
import dietplan.app.oqvvwe.data.db.RecipeDB
import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort
import dietplan.app.oqvvwe.data.remote.entities.RetrofitService
import kotlinx.coroutines.launch

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val retrofit = RetrofitService.getInstance()

    private val dao = AppDatabase.getInstance(application).recipeDao()

    val listRecipes = MutableLiveData<List<RecipeItemShort?>>()

    val errorRequest = MutableLiveData<Boolean>()

    suspend fun getRecipeList(query:String) {
        viewModelScope.launch {
            val recipes = mutableListOf<RecipeItemShort?>()
            val response = retrofit.getRecipeResponse(
                "public",
                query,
                RetrofitService.appId,
                RetrofitService.appKey
            )
            if (response.isSuccessful) {
                if (response.body()?.count == 0){
                    errorRequest.value = true
                    errorRequest.value = false
                }
                response.body()?.hits?.map {
                    recipes.add(it.recipe?.getRecipeShort())
                }
                listRecipes.postValue(recipes)
            }
        }
    }

    suspend fun addRecipe(recipe:RecipeDB){
        viewModelScope.launch {
            dao.insertRecipe(recipe)
        }
    }

}