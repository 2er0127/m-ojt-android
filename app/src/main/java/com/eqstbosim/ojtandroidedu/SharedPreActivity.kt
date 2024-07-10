package com.eqstbosim.ojtandroidedu

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SharedPreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pre)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val saveButton: Button = findViewById(R.id.saveButton)
        val loadButton: Button = findViewById(R.id.loadButton)
        val displayTextView: TextView = findViewById(R.id.displayTextView)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            with(sharedPreferences.edit()) {
                putString("username", username)
                putString("password", password)
                apply()
            }

            displayTextView.text = "정보가 저장되었습니다."
        }

        loadButton.setOnClickListener {
            val username = sharedPreferences.getString("username", "저장된 아이디가 없습니다.")
            val password = sharedPreferences.getString("password", "저장된 비밀번호가 없습니다.")

            displayTextView.text = "저장된 아이디: $username\n저장된 비밀번호: $password"
        }
    }
}