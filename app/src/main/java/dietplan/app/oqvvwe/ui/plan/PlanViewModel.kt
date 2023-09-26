package dietplan.app.oqvvwe.ui.plan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dietplan.app.oqvvwe.data.RecipeRepositoryImpl
import dietplan.app.oqvvwe.data.db.RecipeDB
import kotlinx.coroutines.launch

class PlanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepositoryImpl(application)

    val recipeList = repository.getRecipeList()

    suspend fun deleteRecipe(recipe:RecipeDB){
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }

}