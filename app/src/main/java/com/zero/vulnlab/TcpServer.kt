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
import java.net.ServerSocket
import java.net.Socket

class TCPServer(private val port: Int) : Thread() {
    override fun run() {
        try {
            val serverSocket = ServerSocket(port)
            Log.i("TCPServer", "Server Start - PORT : $port")

            while (true) {
                val clientSocket = serverSocket.accept()
                handleClient(clientSocket)
            }
        } catch (e: IOException) {
            Log.e("TCPServer", "서버 에러: ${e.message}")
        }
    }

    private fun handleClient(clientSocket: Socket) {
        try {
            val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val output = PrintWriter(BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream())), true)

            val receivedData = input.readLine()
            Log.i("TCPServer", "$receivedData")

            output.println("$receivedData")
            clientSocket.close()
        } catch (e: IOException) {
            Log.e("TCPServer", "${e.message}")
        }
    }
}

