package dietplan.app.oqvvwe.ui.second

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import dietplan.app.oqvvwe.MY_DATA
import dietplan.app.oqvvwe.MY_PREFS_STORE
import dietplan.app.oqvvwe.R
import dietplan.app.oqvvwe.databinding.ActivitySecondBinding
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    private val myWeb by lazy { binding.mainWebView }
    private val myPb by lazy { binding.mainPB }
    private val myPrefs by lazy { this.getSharedPreferences(MY_PREFS_STORE, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryToGetData(savedInstanceState)
        checkNotificationPermissions()
        this.actionBar?.hide()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        myWeb.saveState(bundle)
        outState.putBundle("MyRemoteData", bundle)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun tryToGetData(state: Bundle?) {
        CookieManager.getInstance().acceptCookie()
        val bundle = state?.getBundle("MyRemoteData")
        if (bundle != null) {
            myWeb.restoreState(bundle)
        }
        lifecycleScope.launch {
            val savedStr = myPrefs.getString(MY_DATA,"")?:""
            myWeb.loadUrl(savedStr)
            myWeb.settings.domStorageEnabled = true
            myWeb.settings.javaScriptEnabled = true
            myWeb.webViewClient = MyWebClient(myPb,myPrefs)
            myWeb.settings.setSupportZoom(false)
            myWeb.settings.cacheMode =WebSettings.LOAD_NO_CACHE
        }
    }

    private fun checkNotificationPermissions() {
        if (myPrefs.getBoolean("should_request_perms", false)) askNotificationPermission()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->

    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                showRequestPermissionDialog()
                myPrefs.edit().putBoolean("should_request_perms", true).apply()

            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                myPrefs.edit().putBoolean("should_request_perms", true).apply()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showRequestPermissionDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        with(dialogBuilder) {
            setTitle(getString(R.string.dialog_title))
            setMessage(getString(R.string.dialog_text))
            setPositiveButton(getString(R.string.ok_btn)) { dialog, id ->
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                dialog.dismiss()
            }
            setNegativeButton(getString(R.string.no_btn)) { dialog, id ->
                dialog.cancel()
            }
            create()
            show()
        }
    }

}