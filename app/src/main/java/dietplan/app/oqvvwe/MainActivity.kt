package dietplan.app.oqvvwe

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerLib
import com.appsflyer.AppsFlyerProperties
import dietplan.app.oqvvwe.databinding.ActivityMainBinding
import dietplan.app.oqvvwe.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkInternet()
        observeInternetStatus()
        getAppsFlyerData()
    }

    private fun checkInternet() {
        viewModel.changeInternetStatus(checkInternetTransport())
    }

    private fun checkInternetTransport(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true

            else -> false
        }
    }

    private fun observeInternetStatus() {
        viewModel.internetStatus.observe(this, Observer {
            if (!it) showCheckInternetDialog()
        })
    }

    private fun showCheckInternetDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        with(dialogBuilder) {
            setTitle(getString(R.string.dialog_title))
            setMessage(getString(R.string.dialog_text))
            setPositiveButton(getString(R.string.btn_check_internet)) { dialog, id ->
                checkInternet()
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun getAppsFlyerData() {
        val appId = "dietplan.app.oqvvwe"
        val devKey = "wBHGLzsvQWfFDxsGsy7Vvk"
        val deviceId = AppsFlyerLib.getInstance().getAppsFlyerUID(this)

        lifecycleScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://gcdsdk.appsflyer.com/install_data/v4.0/dietplan.app.oqvvwe?devkey=wBHGLzsvQWfFDxsGsy7Vvk&device_id=1695679086027-7024640647135813791")
                .get()
                .addHeader("accept", "application/json")
                .build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                Log.d("RESPONSE", response.body?.string() ?: "")
            }
        }
    }

}