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

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest

class AppHashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_hash)

        val checkIntegrityButton: Button = findViewById(R.id.checkIntegrityButton)
        val hashOriStatus: TextView = findViewById(R.id.hashOriStatus)
        val hashIsStatus: TextView = findViewById(R.id.hashIsStatus)

        val originalHash = "99ba6bbc3bac06ff48216e4eb1b4ad1e9d11a10bb0843278bddc1442089beb76"

        hashOriStatus.text = originalHash

        checkIntegrityButton.setOnClickListener {
            try {
                val currentHash = getAppHash()

                hashIsStatus.text = currentHash

                if (currentHash.equals(originalHash, ignoreCase = true)) {
                    Toast.makeText(this, "무결성 검증 성공", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "무결성 검증 실패", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "오류 발생: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getAppHash(): String {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
        val signatures = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            packageInfo.signingInfo.apkContentsSigners
        } else {
            packageInfo.signatures
        }

        val md = MessageDigest.getInstance("SHA-256")
        for (signature in signatures) {
            md.update(signature.toByteArray())
        }

        val digest = md.digest()
        return digest.joinToString("") { "%02x".format(it) }
    }
}


