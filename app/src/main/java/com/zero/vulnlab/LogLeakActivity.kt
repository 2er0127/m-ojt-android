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

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class LogLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logleak)

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (savedUsername != null && savedPassword != null) {
            Log.d("LogLeakActivity", "Username : $savedUsername")
            Log.d("LogLeakActivity", "Password : $savedPassword")
            Log.d("LogLeakActivity", "PhoneNumber : 01012345678")
            Log.d("LogLeakActivity", "EmpID : K1231128")
        }
    }
}