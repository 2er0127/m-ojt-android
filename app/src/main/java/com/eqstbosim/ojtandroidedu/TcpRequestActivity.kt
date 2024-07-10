package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class TcpRequestActivity : AppCompatActivity() {

    private val serverPort = 12345
    private lateinit var serverThread: Thread
    private var serverSocket: ServerSocket? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcp_request)

        val statusTextView: TextView = findViewById(R.id.statusIsRequest)
        val usernameEditText: EditText = findViewById(R.id.idEditText)
        val passwordEditText: EditText = findViewById(R.id.pwEditText)
        val sendTcpButton: Button = findViewById(R.id.sendTcpButton)

        startServer(statusTextView)

        sendTcpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                sendTcpRequest(username, password, statusTextView)
            } else {
                Toast.makeText(this, "사용자 이름과 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startServer(statusTextView: TextView) {
        serverThread = Thread {
            try {
                serverSocket = ServerSocket(serverPort)
                runOnUiThread {
                    statusTextView.text = "Server started on port $serverPort"
                }

                while (!Thread.currentThread().isInterrupted) {
                    val clientSocket = serverSocket?.accept()
                    val reader = BufferedReader(InputStreamReader(clientSocket?.getInputStream()))
                    val message = reader?.readLine()
                    runOnUiThread {
                        statusTextView.text = "Received: $message"
                    }
                    reader?.close()
                    clientSocket?.close()
                }
            } catch (e: IOException) {
                runOnUiThread {
                    //Toast.makeText(this, "Server error: ${e.message}", Toast.LENGTH_SHORT).show()
                    //statusTextView.text = "Server error: ${e.message}"
                }
            } finally {
                serverSocket?.close()
            }
        }
        serverThread.start()
    }

    private fun sendTcpRequest(username: String, password: String, statusTextView: TextView) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            try {
                val message = "username=$username&password=$password"
                val clientSocket = Socket("127.0.0.1", serverPort)
                val writer = PrintWriter(clientSocket.getOutputStream(), true)
                writer.println(message)
                writer.close()
                clientSocket.close()
                runOnUiThread {
                    statusTextView.text = "Request sent: $message"
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Request failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    statusTextView.text = "Request failed: ${e.message}"
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        serverThread.interrupt()
        serverSocket?.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        serverThread.interrupt()
        serverSocket?.close()
    }
}
