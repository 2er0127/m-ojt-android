/*
 * Copyright (c) 2024 SK shieldus EQST Bosim
 * Author: Seona Lee
 * EQST Bosim OJT Android Edu 2024.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of EQST Bosim.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with EQST Bosim.
 */

package com.eqst.vulnlab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val dbController = DatabaseController(this)
        dbController.createDatabase()

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registerTextView: TextView = findViewById(R.id.registerTextView)

        val userDatabase = UserDatabase(this)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = userDatabase.getUser(username)

            if (user != null && user.password == password) {
                saveLoginInfo(user)

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("username", username)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "아이디나 비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveLoginInfo(user: User) {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", user.username)
        editor.putString("password", user.password)
        editor.apply()
    }
}