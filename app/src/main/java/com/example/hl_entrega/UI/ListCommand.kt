package com.example.hl_entrega

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityListCommandBinding


class ListCommand : AppCompatActivity() {

    private lateinit var binding: ActivityListCommandBinding
    private lateinit var userDatabaseHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurando o View Binding
        binding = ActivityListCommandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabaseHelper = UserDatabaseHelper(this)

        // Obtendo a lista de menus do banco de dados
        val commands = userDatabaseHelper.getAllCommands()

        // Convertendo a lista de Command para uma lista de Strings (descrições) para exibição
        val CommandDescriptions = commands.map { it.descriptionC }

        // Configurando o ArrayAdapter para exibir a lista de menus no ListView
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, CommandDescriptions)
        binding.listViewCommands.adapter = adapter
    }
}
