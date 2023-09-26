package dietplan.app.oqvvwe

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val ONESIGNAL_APP_ID = "2e4cd190-1d66-45aa-8d86-1c8f5fbaf40d"
const val APPSFLYER_DEV_KEY = "wBHGLzsvQWfFDxsGsy7Vvk"

class DPApp:Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }

        AppsFlyerLib.getInstance().init(APPSFLYER_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)

    }

}