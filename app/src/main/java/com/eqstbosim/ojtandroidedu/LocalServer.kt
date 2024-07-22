package com.eqstbosim.ojtandroidedu

import java.net.ServerSocket
import java.net.Socket
import java.io.InputStreamReader
import java.io.BufferedReader
import kotlin.concurrent.thread
import android.util.Log

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
fun main() {
    val server = LocalServer()
    server.startServer()
}

class LocalServer {
    fun startServer() {
        thread {
            try {
                val serverSocket = ServerSocket(12345)
                Log.d("LocalServer", "Server is running on port 12345")

                while (true) {
                    val clientSocket: Socket = serverSocket.accept()
                    val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                    val message = input.readLine()
                    Log.d("LocalServer", "Received message: $message")

                    input.close()
                    clientSocket.close()
                }
            } catch (e: Exception) {
                Log.e("LocalServer", "Error in server", e)
            }
        }
    }
}