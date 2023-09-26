package dietplan.app.oqvvwe.domain

import dietplan.app.oqvvwe.data.db.DataDB

interface DataRepository {

    suspend fun getData():String

    suspend fun editData(data:DataDB)

}