package dietplan.app.oqvvwe.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataDB(
    @ColumnInfo
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @ColumnInfo
    val data:String
)
