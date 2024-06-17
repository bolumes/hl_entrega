package com.example.hl_entrega.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hl_entrega.Models.Command
import com.example.hl_entrega.R

class CommandAdapter(context: Context, commands: List<Command>) :

    ArrayAdapter<Command>(context, 0, commands) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val viewHolder: ViewHolder

        if (itemView == null) {
            // Infla o layout personalizado para o item
            itemView = LayoutInflater.from(context).inflate(R.layout.item_command, parent, false)
            viewHolder = ViewHolder()

            // Associa as views ao ViewHolder
            viewHolder.textViewCommandData = itemView.findViewById(R.id.tvDate)
            viewHolder.textViewCommander = itemView.findViewById(R.id.tvCommander)
            viewHolder.textViewCommandDescription = itemView.findViewById(R.id.tvDescription)

            // Armazena o ViewHolder no tag do itemView para reutilização
            itemView.tag = viewHolder
        } else {
            // Recupera o ViewHolder para reutilização
            viewHolder = itemView.tag as ViewHolder
        }

        // Obter o comando atual
        val currentCommand = getItem(position) as Command

        // Definir os textos dos TextViews com os dados do comando
        viewHolder.textViewCommandData.text = "Data: ${currentCommand.dataC}"
        viewHolder.textViewCommander.text = "Commander: ${currentCommand.commanderC}"  // Assumindo que `commander` é um campo no modelo `Command`
        viewHolder.textViewCommandDescription.text = "Description: ${currentCommand.descriptionC}"

        return itemView!!
    }

    // ViewHolder para manter referências aos TextViews
    private class ViewHolder {
        lateinit var textViewCommandData: TextView
        lateinit var textViewCommander: TextView
        lateinit var textViewCommandDescription: TextView
    }
}
