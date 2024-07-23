package com.eqst.vulnlab

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class TCPClient(private val serverAddress: String, private val port: Int) {

    fun sendMessage(message: String) {
        thread {
            var socket: Socket? = null
            var writer: PrintWriter? = null
            var reader: BufferedReader? = null
            try {
                socket = Socket(serverAddress, port)
                writer = PrintWriter(socket.getOutputStream(), true)
                reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                Log.d("TCPClient", "Sending message: $message")
                writer.println(message)
                writer.flush()
                val response = reader.readLine()
                Log.d("TCPClient", "Received from server: $response")
            } catch (e: Exception) {
                Log.e("TCPClient", "Error in client: ${e.message}")
                e.printStackTrace()
            } finally {
                try {
                    reader?.close()
                    writer?.close()
                    socket?.close()
                } catch (e: Exception) {
                    Log.e("TCPClient", "Error closing client socket: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }
}
