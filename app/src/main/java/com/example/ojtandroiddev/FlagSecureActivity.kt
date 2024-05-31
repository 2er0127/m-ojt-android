package com.example.ojtandroiddev

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FlagSecureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_secure)

        val statusTextView: TextView = findViewById(R.id.statusIsFlag)
        val enableProtectionButton: Button = findViewById(R.id.enableProtectionButton)
        val disableProtectionButton: Button = findViewById(R.id.disableProtectionButton)

        enableProtectionButton.setOnClickListener {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
            statusTextView.text = "화면 보호가 활성화되었습니다."
        }

        disableProtectionButton.setOnClickListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            statusTextView.text = "화면 보호가 비활성화되었습니다."
        }
    }
}
