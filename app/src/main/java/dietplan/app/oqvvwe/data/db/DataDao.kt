package dietplan.app.oqvvwe.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao{

    @Query("SELECT * FROM datadb WHERE id =:id")
    suspend fun getData(id:Int = 1):DataDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(dataDB: DataDB)

}