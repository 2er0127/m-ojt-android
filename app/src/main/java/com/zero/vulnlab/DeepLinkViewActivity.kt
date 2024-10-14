/*
 * Copyright (c) 2024 2er0.oz
 * Author: Seona Lee
 * All rights reserved.
 *
 * This software contains confidential and proprietary information of 2er0.oz.
 * Any unauthorized copying, disclosure, or use of this information is strictly prohibited.
 * Use of this software is governed by the terms of the license agreement you have entered into with 2er0.oz.
 */

package com.zero.vulnlab

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class DeepLinkViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var urlEditText: EditText

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link_view)

        webView = findViewById(R.id.webView)
        urlEditText = findViewById(R.id.urlEditText)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()

        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        val data: Uri? = intent?.data
        val fullUrl = data?.toString()

        urlEditText.setText(fullUrl ?: "zeroapp://vulnlab.com?url=file:///android_asset/encrypted_deeplink.html")

        urlEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val enteredUrl = urlEditText.text.toString()
                val parsedUri = Uri.parse(enteredUrl)

                val parsedUrl = parsedUri.getQueryParameter("url")
                if (parsedUrl != null) {
                    loadUrl(parsedUrl)
                } else {
                    Toast.makeText(this, "잘못된 URL입니다.", Toast.LENGTH_SHORT).show()
                }
                true
            } else {
                false
            }
        }

        if (fullUrl != null) {
            val parsedUrl = Uri.parse(fullUrl).getQueryParameter("url")
            if (parsedUrl != null) {
                loadUrl(parsedUrl)
            }
        }
    }

    private fun loadUrl(url: String) {
        if (url.startsWith("file:///android_asset/encrypted_deeplink.html")) {
            loadEncryptedHtmlInWebView(url)
        } else {
            webView.loadUrl(url)
        }
    }

    private fun loadEncryptedHtmlInWebView(fileName: String) {
        val key = "myaeskeyissblove".toByteArray()
        try {
            val decryptedHtml = decryptHtmlFileFromAssets(fileName, key)
            webView.loadDataWithBaseURL(null, decryptedHtml, "text/html", "UTF-8", null)
        } catch (e: Exception) {
            Toast.makeText(this, "복호화에 실패했습니다: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun decryptHtmlFileFromAssets(fileName: String, key: ByteArray): String {
        val assetPath = fileName.replace("file:///android_asset/", "")
        val inputStream: InputStream = assets.open(assetPath)
        val iv = ByteArray(16)
        inputStream.read(iv)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(key, "AES")
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)

        val cipherInputStream = CipherInputStream(inputStream, cipher)
        return cipherInputStream.bufferedReader().use { it.readText() }
    }

    class WebAppInterface(private val context: DeepLinkViewActivity) {
        @JavascriptInterface
        fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun stealData(data: String) {
            Toast.makeText(context, "Data stolen: $data", Toast.LENGTH_SHORT).show()
        }
    }
}
