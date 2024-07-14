package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class CodeObfuscationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_obfus)

        val statusTextView: TextView = findViewById(R.id.obfusIsStatus)
        val checkObfuscationButton: Button = findViewById(R.id.checkObfuscationButton)

        checkObfuscationButton.setOnClickListener {
            val isObfuscated = isClassObfuscated(MainActivity::class.java)
            if (isObfuscated) {
                statusTextView.text = "난독화되어 있음"
                Toast.makeText(this, "코드가 난독화되어 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                statusTextView.text = "난독화되지 않음"
                Toast.makeText(this, "코드가 난독화되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isClassObfuscated(cls: Class<*>): Boolean {
        val className = cls.simpleName
        return className.length <= 2
    }
}