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

import android.util.Log
import java.io.*
import java.net.Socket

class TCPClient(private val serverIp: String, private val serverPort: Int) {

    fun sendData(data: String) {
        try {
            val socket = Socket(serverIp, serverPort)
            val output = PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
            val input = BufferedReader(InputStreamReader(socket.getInputStream()))

            output.println(data)
            val response = input.readLine()

            Log.i("TCPClient", "Server Response : $response")
            socket.close()
        } catch (e: IOException) {
            Log.e("TCPClient", "${e.message}")
        }
    }
}
