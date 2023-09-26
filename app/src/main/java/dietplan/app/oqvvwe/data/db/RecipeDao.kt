package dietplan.app.oqvvwe.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipedb")
    fun getRecipesList():LiveData<List<RecipeDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeDB: RecipeDB)

    @Query("DELETE FROM recipedb")
    suspend fun clearRecipe()

//    @Query("DELETE FROM recipedb WHERE uri =:uri")
    @Delete
    suspend fun deleteRecipe(recipe:RecipeDB)

}