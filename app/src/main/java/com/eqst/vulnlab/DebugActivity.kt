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
import android.os.Bundle
import android.os.Debug
import android.widget.Button
import android.widget.TextView
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity

class DebugActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_debugging)

        val statusTextView: TextView = findViewById(R.id.debugIsStatus)
        val checkDebuggingButton: Button = findViewById(R.id.checkDebuggingButton)

        checkDebuggingButton.setOnClickListener {
            if (isDebugging()) {
                statusTextView.text = "디버깅 활성화"
                makeText(this, "디버깅이 감지되었습니다.", LENGTH_SHORT).show()
            } else {
                statusTextView.text = "디버깅 비활성화"
                makeText(this, "디버깅이 감지되지 않았습니다.", LENGTH_SHORT).show()
            }
        }
    }

    private fun isDebugging(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }
}