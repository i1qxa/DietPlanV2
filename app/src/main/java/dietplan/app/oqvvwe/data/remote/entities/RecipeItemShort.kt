package dietplan.app.oqvvwe.data.remote.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.edamantestapp.data.remoteData.entities.IngredientItem
import dietplan.app.oqvvwe.data.db.RecipeDB
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItemShort(
    val uri:String,
    val label:String?,
    val imgRegular:String?,
    val imgSmall:String?,
    val calories:Int?,
    val totalTime:Double?,
    val ingredientsList:List<IngredientItem>
):Parcelable{
    fun getRecipeDB() = RecipeDB(
        uri, label, imgSmall, calories
    )
}
