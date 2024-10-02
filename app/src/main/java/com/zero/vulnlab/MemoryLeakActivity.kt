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
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MemoryLeakActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var inputUsername: String? = null
    private var inputPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoryleak)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        inputUsername = sharedPreferences.getString("username", null)
        inputPassword = sharedPreferences.getString("password", null)

        if (inputUsername != null && inputPassword != null) {
            handleSensitiveData(inputUsername!!, inputPassword!!)
        }
    }

    private fun handleSensitiveData(username: String, password: String) {
        val sensitiveInfo = "Username: $username, Password: $password"

    }
}