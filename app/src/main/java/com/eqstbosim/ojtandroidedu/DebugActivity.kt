package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Debug
import android.widget.Button
import android.widget.TextView
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class DebugActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_debugging)

        val statusTextView: TextView = findViewById(R.id.debugIsStatus)
        val checkDebuggingButton: Button = findViewById(R.id.checkDebuggingButton)

        checkDebuggingButton.setOnClickListener {
            if (isDebugging()) {
                statusTextView.text = "디버깅 활성화"
                makeText(this, "디버깅이 감지되었습니다.", LENGTH_SHORT).show()
            } else {
                statusTextView.text = "디버깅 비활성화"
                makeText(this, "디버깅이 감지되지 않았습니다.", LENGTH_SHORT).show()
            }
        }
    }

    private fun isDebugging(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }
}