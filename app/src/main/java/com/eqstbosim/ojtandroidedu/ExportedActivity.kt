package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class ExportedActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_true_ex)

        val usernameEditText: EditText = findViewById(R.id.adminUsernameEditText)
        val passwordEditText: EditText = findViewById(R.id.adminPasswordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "1q2w3e4r5t!@oz") {
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}