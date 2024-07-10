package com.eqstbosim.ojtandroidedu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CodeObfuscationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_obfus)

        val statusTextView: TextView = findViewById(R.id.statusIsObfus)
        val checkObfuscationButton: Button = findViewById(R.id.checkObfuscationButton)

        checkObfuscationButton.setOnClickListener {
            val isObfuscated = isClassObfuscated(MainActivity::class.java)
            if (isObfuscated) {
                statusTextView.text = "코드 난독화"
                Toast.makeText(this, "코드가 난독화되어 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                statusTextView.text = "코드 난독화되지 않음"
                Toast.makeText(this, "코드가 난독화되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isClassObfuscated(cls: Class<*>): Boolean {
        val className = cls.simpleName
        return className.length <= 2
    }
}