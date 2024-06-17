package com.example.hl_entrega.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hl_entrega.databinding.ActivitySeeMenuUserBinding

class SeeMenuUser : AppCompatActivity() {

    private lateinit var binding: ActivitySeeMenuUserBinding
    private lateinit var userDatabaseHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurando o View Binding
        binding = ActivitySeeMenuUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabaseHelper = UserDatabaseHelper(this)

        // Obtendo a lista de menus do banco de dados
        val menus = userDatabaseHelper.getAllMenus()

        // Verificando se a lista de menus não está vazia
        if (menus.isNotEmpty()) {
            // Pegando o último menu
            val lastMenu = menus.last()

            // Configurando o RecyclerView
            val adapter = SeeMenuAdapter(lastMenu)
            binding.recyclerViewMenu.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewMenu.adapter = adapter
        }

        binding.btnDoComm.setOnClickListener {
            navigateToDocommand()
        }

        binding.btnBack.setOnClickListener {
            navigateToMenuUser()
        }
    }

    private fun navigateToDocommand(){
        val navigateCommand = Intent(this, CommandActivity::class.java)
        startActivity(navigateCommand)
    }

    private fun navigateToMenuUser(){
        val navigateMenuUser = Intent(this, MenuUserActivity::class.java)
        startActivity(navigateMenuUser)
    }
}
