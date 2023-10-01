package dietplan.app.oqvvwe.ui.second

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import dietplan.app.oqvvwe.data.DataRepositoryImpl
import dietplan.app.oqvvwe.data.db.DataDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyWebClient(
    private val progress: View,
    private val application: Application
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
        TODO("NEED TO GET APP_LINK_SAVE")
        if (adress.contains("app_link_save")) {
            val newUrl = ""
            val repo = DataRepositoryImpl(application)
            }
    }



}