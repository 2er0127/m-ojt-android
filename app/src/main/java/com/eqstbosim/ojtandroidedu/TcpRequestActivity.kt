package com.eqstbosim.ojtandroidedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.util.Log

class TcpRequestActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var pwEditText: EditText
    private lateinit var sendTcpButton: Button
    private lateinit var startServerButton: Button
    private lateinit var stopServerButton: Button
    private lateinit var requestIsStatus: TextView
    private lateinit var tcpServer: TCPServer
    private lateinit var tcpClient: TCPClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcp_request)

        idEditText = findViewById(R.id.idEditText)
        pwEditText = findViewById(R.id.pwEditText)
        sendTcpButton = findViewById(R.id.sendTcpButton)
        startServerButton = findViewById(R.id.startServerButton)
        stopServerButton = findViewById(R.id.stopServerButton)
        requestIsStatus = findViewById(R.id.requestIsStatus)

        tcpServer = TCPServer(12345)
        tcpClient = TCPClient("127.0.0.1", 12345) // localhost

        startServerButton.setOnClickListener {
            tcpServer.startServer()
            Log.d("TcpRequestActivity", "Server started")
            requestIsStatus.text = "Server started !!"
        }

        stopServerButton.setOnClickListener {
            tcpServer.stopServer()
            Log.d("TcpRequestActivity", "Server stopped")
            requestIsStatus.text = "Server stopped."
        }

        sendTcpButton.setOnClickListener {
            val id = idEditText.text.toString()
            val pw = pwEditText.text.toString()
            val message = "ID: $id, PW: $pw"
            Log.d("TcpRequestActivity", "Sending message: $message")
            tcpClient.sendMessage(message)
            requestIsStatus.text = "Message sent... (check logcat)"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tcpServer.stopServer()
    }
}
