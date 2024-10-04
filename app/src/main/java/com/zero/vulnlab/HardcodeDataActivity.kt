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

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class HardcodeDataActivity : AppCompatActivity() {
    private val testID = "test"
    private val testPassword = "P@ssw0rd!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hardcode)

        Log.d("HardcodeDataActivity", "test ID: $testID")
        Log.d("HardcodeDataActivity", "test Password: $testPassword")
        Log.d("HardcodeDataActivity", "해당 계정으로 로그인도 가능!")
    }
}