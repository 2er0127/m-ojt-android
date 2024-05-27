package com.example.ojtandroiddev

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.security.MessageDigest
import android.util.Base64

class AppHashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_hash)

        val statusTextView: TextView = findViewById(R.id.statusIsHash)
        val checkIntegrityButton: Button = findViewById(R.id.checkIntegrityButton)

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
        val expectedHash = "a2d2c85995198afb0bc4d9f3d7b0ea0c33e1381b6e107ddec374765af7563d2d"
        val appHash = calculateApkHash()
        return appHash == expectedHash
    }

    private fun calculateApkHash(): String? {
        return try {
            val packageManager = packageManager
            val packageName = packageName
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val apkPath = packageInfo.applicationInfo.sourceDir
            val fis = FileInputStream(apkPath)
            val digest = MessageDigest.getInstance("SHA-256")
            val buffer = ByteArray(1024)
            var count: Int
            while (fis.read(buffer).also { count = it } > 0) {
                digest.update(buffer, 0, count)
            }
            fis.close()
            val hashBytes = digest.digest()
            Base64.encodeToString(hashBytes, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

