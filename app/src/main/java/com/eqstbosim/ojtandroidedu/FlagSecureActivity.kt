package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class FlagSecureActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_secure)

        val statusTextView: TextView = findViewById(R.id.flagIsStatus)
        val enableProtectionButton: Button = findViewById(R.id.enableProtectionButton)
        val disableProtectionButton: Button = findViewById(R.id.disableProtectionButton)

        enableProtectionButton.setOnClickListener {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
            statusTextView.text = "화면 보호 활성화"
            Toast.makeText(this, "화면 보호가 활성화되었습니다.", Toast.LENGTH_SHORT).show()
        }

        disableProtectionButton.setOnClickListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            statusTextView.text = "화면 보호 비활성화"
            Toast.makeText(this, "화면 보호가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
