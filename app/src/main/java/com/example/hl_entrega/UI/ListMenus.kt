package com.example.hl_entrega

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityListMenusBinding


class ListMenus : AppCompatActivity() {

        private lateinit var binding: ActivityListMenusBinding
        private lateinit var userDatabaseHelper: UserDatabaseHelper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Configurando o View Binding
            binding = ActivityListMenusBinding.inflate(layoutInflater)
            setContentView(binding.root)

            userDatabaseHelper = UserDatabaseHelper(this)

            // Obtendo a lista de menus do banco de dados
            val menus = userDatabaseHelper.getAllMenus()

            // Convertendo a lista de Menu para uma lista de Strings (descrições) para exibição
            val menuDescriptions = menus.map { it.descriptionM }

            // Configurando o ArrayAdapter para exibir a lista de menus no ListView
            val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, menuDescriptions)
            binding.listViewMenu.adapter = adapter
        }
    }
