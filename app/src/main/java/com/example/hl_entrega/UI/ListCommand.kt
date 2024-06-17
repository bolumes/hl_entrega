package com.example.hl_entrega.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.Models.Command
import com.example.hl_entrega.R
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

        // Obtendo a lista de comandos do banco de dados
        val commands = userDatabaseHelper.getAllCommands()

        // Configurando o ArrayAdapter personalizado para exibir a lista de comandos no ListView
        val adapter = CommandAdapter(this, commands)
        binding.listViewCommands.adapter = adapter
    }

    // Adapter personalizado para exibir a data e a descrição do comando
    class CommandAdapter(context: Context, private val commands: List<Command>) :
        ArrayAdapter<Command>(context, 0, commands) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val command = getItem(position)
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_command, parent, false)

            val tvDate = view.findViewById<TextView>(R.id.tvDate)
            val tvComm = view.findViewById<TextView>(R.id.tvCommander)
            val tvDescription = view.findViewById<TextView>(R.id.tvDescription)

            // Verificando se o comando não é nulo
            command?.let {
                tvDate.text = it.dataC
                tvDescription.text = it.descriptionC
                tvComm.text = it.commanderC
            }

            return view
        }
    }
}
