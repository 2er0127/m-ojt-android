/*
 * Copyright (c) 2024 2er0.oz
 * Author: Seona Lee
 * All rights reserved.
 *
 * This software contains confidential and proprietary information of 2er0.oz.
 * Any unauthorized copying, disclosure, or use of this information is strictly prohibited.
 * Use of this software is governed by the terms of the license agreement you have entered into with 2er0.oz.
 */

package com.zero.vulnlab

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var loggedInUserTextView: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loggedInUserTextView = findViewById(R.id.loggedInUserTextView)

        val username = intent.getStringExtra("username") ?: ""
        loggedInUserTextView.text = "현재 [ ${username} ] 사용자가 로그인 중입니다."

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val button10: Button = findViewById(R.id.button10)
        val button11: Button = findViewById(R.id.button11)
        val button12: Button = findViewById(R.id.button12)

        button1.setOnClickListener { startButton1Activity() }
        button2.setOnClickListener { startButton2Activity() }
        button3.setOnClickListener { startButton3Activity() }
        button4.setOnClickListener { startButton4Activity() }
        button5.setOnClickListener { startButton5Activity() }
        button6.setOnClickListener { startButton6Activity() }
        button7.setOnClickListener { startButton7Activity() }
        button8.setOnClickListener { startButton8Activity() }
        button9.setOnClickListener { startButton9Activity() }
        button10.setOnClickListener { startButton10Activity() }
        button11.setOnClickListener { startButton11Activity() }
        button12.setOnClickListener { startButton12Activity() }

        if (username == "admin") {
            val userListButton = Button(this).apply {
                text = "User List"
                setOnClickListener { startUserListActivity() }
            }
            findViewById<LinearLayout>(R.id.buttonContainer).addView(userListButton)
        }
    }

    private fun startButton1Activity() {
        val intent = Intent(this, IsRootedActivity::class.java)
        startActivity(intent)
    }

    private fun startButton2Activity() {
        val intent = Intent(this, AppHashActivity::class.java)
        startActivity(intent)
    }

    private fun startButton3Activity() {
        val intent = Intent(this, CodeObfuscationActivity::class.java)
        startActivity(intent)
    }

    private fun startButton4Activity() {
        val intent = Intent(this, DebugActivity::class.java)
        startActivity(intent)
    }

    private fun startButton5Activity() {
        val intent = Intent(this, ExportedActivity::class.java)
        startActivity(intent)
    }

    private fun startButton6Activity() {
        val intent = Intent(this, FlagSecureActivity::class.java)
        startActivity(intent)
    }

    private fun startButton7Activity() {
        val intent = Intent(this, SharedPreActivity::class.java)
        startActivity(intent)
    }

    private fun startButton8Activity() {
        val intent = Intent(this, HttpRequestActivity::class.java)
        startActivity(intent)
    }

    private fun startButton9Activity() {
        val intent = Intent(this, DeepLinkActivity::class.java)
        startActivity(intent)
    }

    private fun startButton10Activity() {
        val intent = Intent(this, HardcodeDataActivity::class.java)
        startActivity(intent)
    }

    private fun startButton11Activity() {
        val intent = Intent(this, MemoryLeakActivity::class.java)
        startActivity(intent)
    }

    private fun startButton12Activity() {
        val intent = Intent(this, LogLeakActivity::class.java)
        startActivity(intent)
    }

    private fun startUserListActivity() {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }
}
