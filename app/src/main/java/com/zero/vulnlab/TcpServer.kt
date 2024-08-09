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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class TCPServer(private val port: Int) {

    private var serverSocket: ServerSocket? = null
    private var isRunning = false

    fun startServer() {
        isRunning = true
        thread {
            try {
                serverSocket = ServerSocket(port)
                Log.d("TCPServer", "Server started on port $port")

                while (isRunning) {
                    val clientSocket = serverSocket!!.accept()
                    Log.d("TCPServer", "Client connected: ${clientSocket.inetAddress.hostAddress}")
                    handleClient(clientSocket)
                }
            } catch (e: Exception) {
                Log.e("TCPServer", "Error in server: ${e.message}")
                e.printStackTrace()
            } finally {
                stopServer()
            }
        }
    }

    private fun handleClient(clientSocket: Socket) {
        thread {
            var reader: BufferedReader? = null
            var writer: PrintWriter? = null
            try {
                reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                writer = PrintWriter(clientSocket.getOutputStream(), true)

                var message: String?
                while (reader.readLine().also { message = it } != null) {
                    Log.d("TCPServer", "Received: $message")
                    writer.println("Echo: $message")
                }
            } catch (e: Exception) {
                Log.e("TCPServer", "Error handling client: ${e.message}")
                e.printStackTrace()
            } finally {
                try {
                    reader?.close()
                    writer?.close()
                    clientSocket.close()
                } catch (e: Exception) {
                    Log.e("TCPServer", "Error closing client socket: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    fun stopServer() {
        isRunning = false
        try {
            serverSocket?.close()
            Log.d("TCPServer", "Server stopped")
        } catch (e: Exception) {
            Log.e("TCPServer", "Error stopping server: ${e.message}")
            e.printStackTrace()
        }
    }
}