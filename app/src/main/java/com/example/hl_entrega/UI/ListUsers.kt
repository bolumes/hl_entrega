package com.example.hl_entrega

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListUsers : AppCompatActivity() {

    private lateinit var userDatabaseHelper: UserDatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        userDatabaseHelper = UserDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewUsers)

        val usersList = userDatabaseHelper.getAllUsers()
        userAdapter = UserAdapter(usersList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter
    }
}
