package com.eqstbosim.ojtandroidedu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DeepLinkActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val password = sharedPreferences.getString("password", "")

        val deepLinkButton: Button = findViewById(R.id.deepLinkButton)
        deepLinkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("myapp://login?password=$password")
            startActivity(intent)
        }
    }
}
