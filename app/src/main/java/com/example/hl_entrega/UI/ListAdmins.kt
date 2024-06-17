package com.example.hl_entrega.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hl_entrega.R

class ListAdmins : AppCompatActivity() {

    private lateinit var userDatabaseHelper: UserDatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adminAdapter: AdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_admins)

        userDatabaseHelper = UserDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewAdmins)

        val adminsList = userDatabaseHelper.getAllAdmins()
        adminAdapter = AdminAdapter(adminsList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adminAdapter
    }
}
