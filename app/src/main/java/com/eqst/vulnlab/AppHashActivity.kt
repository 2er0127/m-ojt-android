/*
 * Copyright (c) 2024 2er0.oz
 * Author: Seona Lee
 * All rights reserved.
 *
 * This software contains confidential and proprietary information of 2er0.oz.
 * Any unauthorized copying, disclosure, or use of this information is strictly prohibited.
 * Use of this software is governed by the terms of the license agreement you have entered into with 2er0.oz.
 */

package com.eqst.vulnlab

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.zip.ZipFile
import java.io.File

class AppHashActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_hash)

        val statusTextView: TextView = findViewById(R.id.hashIsStatus)
        val checkIntegrityButton: Button = findViewById(R.id.checkIntegrityButton)

        saveDexCrc()

        checkIntegrityButton.setOnClickListener {
            val isAppIntegrityValid = checkAppIntegrity()
            if (isAppIntegrityValid) {
                statusTextView.text = "앱 무결성 검증 성공"
                Toast.makeText(this, "무결성 검증 성공", Toast.LENGTH_SHORT).show()
            } else {
                statusTextView.text = "앱 무결성 검증 실패"
                Toast.makeText(this, "무결성 검증 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAppIntegrity(): Boolean {
        val dexCrcFromFile = readDexCrcFromFile()
        val dexCrc = calculateDexCrc()
        return dexCrc != null && dexCrc == dexCrcFromFile
    }

    private fun calculateDexCrc(): Long? {
        return try {
            val packageManager = packageManager
            val packageName = packageName
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val apkPath = packageInfo.applicationInfo.sourceDir
            val zipFile = ZipFile(apkPath)
            val dexEntry = zipFile.getEntry("classes.dex")
            val dexCrc = dexEntry.crc
            zipFile.close()
            dexCrc
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun readDexCrcFromFile(): Long? {
        return try {
            val file = File(filesDir, "dex_crc.txt")
            if (!file.exists()) {
                null
            } else {
                file.readText().toLong()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun writeDexCrcToFile(dexCrc: Long) {
        try {
            val file = File(filesDir, "dex_crc.txt")
            file.writeText(dexCrc.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveDexCrc() {
        val dexCrc = calculateDexCrc()
        if (dexCrc != null) {
            writeDexCrcToFile(dexCrc)
        }
    }
}
