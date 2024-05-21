package com.example.ojtandroiddev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)

        button1.setOnClickListener { startButton1Activity() }
        button2.setOnClickListener { startButton2Activity() }
        button3.setOnClickListener { startButton3Activity() }
        button4.setOnClickListener { startButton4Activity() }
        button5.setOnClickListener { startButton5Activity() }
        button6.setOnClickListener { startButton6Activity() }
    }

    private fun startButton1Activity() {
        val intent = Intent(this, IsRootedActivity::class.java)
        startActivity(intent)
    }

    private fun startButton2Activity() {
        val intent = Intent(this, AppHashActivity::class.java)
        startActivity(intent)
    }

    private fun startButton3Activity() {
        val intent = Intent(this, CodeObfuscationActivity::class.java)
        startActivity(intent)
    }

    private fun startButton4Activity() {
        val intent = Intent(this, SharedPreActivity::class.java)
        startActivity(intent)
    }

    private fun startButton5Activity() {
        val intent = Intent(this, ExportedActivity::class.java)
        startActivity(intent)
    }

    private fun startButton6Activity() {
        val intent = Intent(this, DebugActivity::class.java)
        startActivity(intent)
    }
}
