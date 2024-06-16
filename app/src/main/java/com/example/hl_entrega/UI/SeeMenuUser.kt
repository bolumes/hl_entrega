package com.example.hl_entrega

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
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
            // Pegando a descrição do último menu
            val lastMenuDescription = menus.last().descriptionM

            // Configurando o ArrayAdapter para exibir apenas o último item no ListView
            val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listOf(lastMenuDescription))
            binding.listViewMenu.adapter = adapter
        }
    }
}
