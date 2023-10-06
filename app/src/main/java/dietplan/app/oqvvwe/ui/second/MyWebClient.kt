package dietplan.app.oqvvwe.ui.second

import android.content.SharedPreferences
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import dietplan.app.oqvvwe.MY_DATA

class MyWebClient(
    private val progress: View,
    private val prefs: SharedPreferences,
) : WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        progress.visibility = View.GONE
        view?.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        checkPath(url ?: "")
        return false
    }

    private fun checkPath(adress: String) {
        val regex = Regex("""(https://[a-z\d./-]*)""")
        val newAdress = regex.find(adress)?.value
        if (newAdress != null) {
            prefs.edit().putString(MY_DATA, newAdress).apply()
        }
    }


}