package dietplan.app.oqvvwe.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeDB(
    @PrimaryKey
    val uri: String,
    val label: String?,
    val imgSmall: String?,
    val calories: Int?
    )
