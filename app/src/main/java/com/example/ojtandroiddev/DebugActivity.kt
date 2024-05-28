package com.example.ojtandroiddev

import android.os.Bundle
import android.os.Debug
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity

class DebugActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_debugging)

        val statusTextView: TextView = findViewById(R.id.debugStatusTextView)
        val checkDebuggingButton: Button = findViewById(R.id.checkDebuggingButton)

        checkDebuggingButton.setOnClickListener {
            if (isDebugging()) {
                statusTextView.text = "디버깅이 감지되었습니다."
                makeText(this, "디버깅 모드 활성화", LENGTH_SHORT).show()
            } else {
                statusTextView.text = "디버깅이 감지되지 않았습니다."
                makeText(this, "디버깅 모드 비활성화", LENGTH_SHORT).show()
            }
        }
    }

    private fun isDebugging(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }
}