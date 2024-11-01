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
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DeepLinkActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)

        val deepLinkButton: Button = findViewById(R.id.deepLinkButton)
        deepLinkButton.setOnClickListener {
            val intent = Intent(this, DeepLinkViewActivity::class.java)
            val uri = Uri.parse("zeroapp://vulnlab.com?url=file:///android_asset/encrypted_deeplink.html")
            intent.data = uri
            startActivity(intent)
        }
    }
}
