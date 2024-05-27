package com.example.ojtandroiddev

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class IsRootedActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_rooted)

        val statusTextView: TextView = findViewById(R.id.statusIsRooted)
        val checkRootButton: Button = findViewById(R.id.rootCheckButton)

        checkRootButton.setOnClickListener {
            val isRooted = isDeviceRooted()
            if (isRooted) {
                statusTextView.text = "루팅 탐지"
                Toast.makeText(this, "루팅된 기기입니다.", Toast.LENGTH_SHORT).show()
            } else {
                statusTextView.text = "루팅이 탐지되지 않음"
                Toast.makeText(this, "루팅되지 않은 기기입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isDeviceRooted(): Boolean {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3()
    }

    private fun checkRootMethod1(): Boolean {
        val paths = arrayOf(
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su"
        )
        for (path in paths) {
            if (File(path).exists()) {
                return true
            }
        }
        return false
    }
    private fun checkRootMethod2(): Boolean {
        val packages = arrayOf(
            "com.noshufou.android.su",
            "com.thirdparty.superuser",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.zachspong.temprootremovejb",
            "com.ramdroid.appquarantine"
        )
        val packageManager = packageManager
        for (pkg in packages) {
            try {
                packageManager.getPackageInfo(pkg, 0)
                return true
            } catch (e: Exception) {
            }
        }
        return false
    }

    private fun checkRootMethod3(): Boolean {
        val buildTags = android.os.Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }
}

