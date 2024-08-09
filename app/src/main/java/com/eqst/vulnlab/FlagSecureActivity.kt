/*
 * Copyright (c) 2024 2er0.oz
 * Author: Seona Lee
 * All rights reserved.
 *
 * This software contains confidential and proprietary information of 2er0.oz.
 * Any unauthorized copying, disclosure, or use of this information is strictly prohibited.
 * Use of this software is governed by the terms of the license agreement you have entered into with 2er0.oz.
 */

package com.eqst.vulnlab

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FlagSecureActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_secure)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val statusTextView: TextView = findViewById(R.id.flagIsStatus)
        val enableProtectionButton: Button = findViewById(R.id.enableProtectionButton)
        val disableProtectionButton: Button = findViewById(R.id.disableProtectionButton)
        val simTextView: TextView = findViewById(R.id.simTextView)

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

        val username = sharedPreferences.getString("username", "Unknown")
        val password = sharedPreferences.getString("password", "Unknown")
        simTextView.text = "현재 로그인된 아이디: $username\n비밀번호: $password"
    }
}
