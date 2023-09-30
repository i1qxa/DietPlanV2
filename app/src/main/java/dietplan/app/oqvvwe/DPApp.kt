package dietplan.app.oqvvwe

import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val ONESIGNAL_APP_ID = "2e4cd190-1d66-45aa-8d86-1c8f5fbaf40d"
const val APPSFLYER_DEV_KEY = "wBHGLzsvQWfFDxsGsy7Vvk"
const val APPS_LOG_TAG ="APPSF"

class DPApp:Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }

        AppsFlyerLib.getInstance().init(APPSFLYER_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().setDebugLog(true)
        AppsFlyerLib.getInstance().start(this, APPSFLYER_DEV_KEY, object :AppsFlyerRequestListener{
            override fun onSuccess() {
                Log.d(APPS_LOG_TAG, "Launch sent successfully")
            }

            override fun onError(p0: Int, p1: String) {
                Log.d(
                    APPS_LOG_TAG, "Launch failed to be sent:\n" +
                        "Error code: " + p0 + "\n"
                        + "Error description: " + p1)
            }
            }
        )

    }


}