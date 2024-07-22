package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket
import android.util.Log

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class TcpRequestActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var pwEditText: EditText
    private lateinit var sendTcpButton: Button
    private lateinit var requestIsStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcp_request)

        idEditText = findViewById(R.id.idEditText)
        pwEditText = findViewById(R.id.pwEditText)
        sendTcpButton = findViewById(R.id.sendTcpButton)
        requestIsStatus = findViewById(R.id.requestIsStatus)

        // 서버 시작
        val server = LocalServer()
        server.startServer()

        sendTcpButton.setOnClickListener {
            val id = idEditText.text.toString()
            val pw = pwEditText.text.toString()
            val message = "ID: $id, PW: $pw"
            SendMessageTask().execute(message)
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class SendMessageTask : AsyncTask<String, Void, String>() {
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: String?): String? {
            val message = params[0]
            return try {
                val socket = Socket("127.0.0.1", 12345) // 로컬 IP 주소
                val output = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
                Log.d("SendMessageTask", "Sending message: $message")
                output.write(message)
                output.newLine()
                output.flush()
                output.close()
                socket.close()
                "Message sent successfully"
            } catch (e: Exception) {
                Log.e("SendMessageTask", "Failed to send message", e)
                "Failed to send message"
            }
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            requestIsStatus.text = result
        }
    }
}