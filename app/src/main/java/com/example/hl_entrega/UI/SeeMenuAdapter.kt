package com.example.hl_entrega

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hl_entrega.Models.Menu

class SeeMenuAdapter(private val menus: List<Menu>) : RecyclerView.Adapter<SeeMenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuData: TextView = itemView.findViewById(R.id.tvMenuData)
        val menuDescription: TextView = itemView.findViewById(R.id.tvMenuDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seemenu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        // Sempre pegue o último item da lista
        val menu = menus.last()
        holder.menuData.text = menu.dataM
        holder.menuDescription.text = menu.descriptionM
    }

    override fun getItemCount(): Int {
        // Retorne 1 para exibir apenas um item
        return 1
    }
}
