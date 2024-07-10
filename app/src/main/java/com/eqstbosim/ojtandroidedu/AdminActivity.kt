package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class AdminActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val adminInfoTextView: TextView = findViewById(R.id.adminTextView)
    }
}