//package dietplan.app.oqvvwe.data
//
//import android.app.Application
//import dietplan.app.oqvvwe.data.db.AppDatabase
//import dietplan.app.oqvvwe.data.db.DataDB
//import dietplan.app.oqvvwe.domain.DataRepository
//
//class DataRepositoryImpl(application: Application):DataRepository {
//
//    private val dao = AppDatabase.getInstance(application).dataDao()
//
//    override suspend fun getData(): String {
//        return dao.getData().data
//    }
//
//    override suspend fun editData(data: DataDB) {
//        dao.insertData(dao.getData())
//    }
//}