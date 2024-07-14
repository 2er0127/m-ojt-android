package com.eqstbosim.ojtandroidedu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class UserListActivity : AppCompatActivity() {

    private lateinit var userDatabase: UserDatabase
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userDatabase = UserDatabase(this)

        val userRecyclerView: RecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userDatabase.getAllUsers().toMutableList(), userDatabase)
        userRecyclerView.adapter = userAdapter
    }

    override fun onDestroy() {
        userDatabase.close()
        super.onDestroy()
    }
}
