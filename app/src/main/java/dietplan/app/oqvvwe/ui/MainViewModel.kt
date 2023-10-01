package dietplan.app.oqvvwe.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    val internetStatus = MutableLiveData<Boolean>()

    fun changeInternetStatus(status:Boolean){
        internetStatus.value = status
    }



}