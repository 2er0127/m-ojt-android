package com.eqstbosim.ojtandroidedu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.util.zip.ZipFile

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
        val expectedDexHash = "808fd0f46acf0a351f6d6cbde74d0dd0b6de5d710f7c95298a1444f72594e41d"
        val dexHash = calculateDexHash()
        return dexHash == expectedDexHash
    }

    private fun calculateDexHash(): String? {
        return try {
            val packageManager = packageManager
            val packageName = packageName
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val apkPath = packageInfo.applicationInfo.sourceDir
            val zipFile = ZipFile(apkPath)
            val dexEntry = zipFile.getEntry("classes.dex")
            val inputStream = zipFile.getInputStream(dexEntry)

            val digest = MessageDigest.getInstance("SHA-256")
            val buffer = ByteArray(1024)
            var count: Int
            while (inputStream.read(buffer).also { count = it } > 0) {
                digest.update(buffer, 0, count)
            }
            inputStream.close()
            zipFile.close()

            val hashBytes = digest.digest()
            hashBytes.joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
