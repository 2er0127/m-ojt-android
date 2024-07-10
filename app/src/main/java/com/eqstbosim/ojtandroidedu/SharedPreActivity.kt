package com.eqstbosim.ojtandroidedu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class SharedPreActivity : AppCompatActivity() {

    private lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pre)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val saveButton: Button = findViewById(R.id.saveButton)
        val loadButton: Button = findViewById(R.id.loadButton)
        val displayTextView: TextView = findViewById(R.id.displayTextView)

        db = UserDatabase(this)

        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = User(username, password)
            db.addUser(user)

            displayTextView.text = "정보가 저장되었습니다."
        }

        loadButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val user = db.getUser(username)

            if (user != null) {
                displayTextView.text = "아이디: ${user.username}\n비밀번호: ${user.password}"
            } else {
                displayTextView.text = "저장된 아이디가 없습니다."
            }
        }
    }
}
