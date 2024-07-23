package com.eqst.vulnlab

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class SharedPreActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pre)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val saveButton: Button = findViewById(R.id.saveButton)
        val loadButton: Button = findViewById(R.id.loadButton)
        val displayTextView: TextView = findViewById(R.id.displayTextView)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            with(sharedPreferences.edit()) {
                putString(KEY_USERNAME, username)
                putString(KEY_PASSWORD, password)
                apply()
            }

            displayTextView.text = "정보가 저장되었습니다."
        }

        loadButton.setOnClickListener {
            // Load from SharedPreferences
            val savedUsername = sharedPreferences.getString(KEY_USERNAME, null)
            val savedPassword = sharedPreferences.getString(KEY_PASSWORD, null)
            if (savedUsername != null && savedPassword != null) {
                displayTextView.text = "아이디: $savedUsername\n비밀번호: $savedPassword"
            } else {
                displayTextView.text = "저장된 정보가 없습니다."
            }
        }
    }

    companion object {
        private const val PREFS_NAME = "UserPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
