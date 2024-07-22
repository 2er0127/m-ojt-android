package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class DeepLinkViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link_view)

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        val data: Uri? = intent?.data
        Log.d("DeepLinkViewActivity", "Received URI: $data")
        if (data != null) {
            val password = data.getQueryParameter("password")
            Log.d("DeepLinkViewActivity", "Password: $password")

            if (password != null) {
                val encodedPassword = Uri.encode(password)
                val url = "file:///android_asset/deeplink.html?password=$encodedPassword"
                webView.loadUrl(url)
            } else {
                webView.loadUrl("file:///android_asset/deeplink.html")
            }
        } else {
            webView.loadUrl("file:///android_asset/deeplink.html")
        }
    }
}
