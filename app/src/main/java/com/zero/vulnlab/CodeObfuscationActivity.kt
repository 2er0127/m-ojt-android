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
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CodeObfuscationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_obfus)

        val statusTextView: TextView = findViewById(R.id.obfusIsStatus)
        val checkObfuscationButton: Button = findViewById(R.id.checkObfuscationButton)

        checkObfuscationButton.setOnClickListener {
            val isObfuscated = isClassObfuscated(CodeObfuscationActivity::class.java)
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
        return try {
            cls.getDeclaredMethod("isClassObfuscated", Class::class.java)
            false
        } catch (e: NoSuchMethodException) {
            true
        }
    }
}
