package com.example.hl_entrega.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hl_entrega.Models.Menu
import com.example.hl_entrega.R

class MenuAdapter(context: Context, menus: List<Menu>) :
    ArrayAdapter<Menu>(context, 0, menus) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val viewHolder: ViewHolder

        if (itemView == null) {
            // Inflate the custom layout for the item
            itemView = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
            viewHolder = ViewHolder()

            // Bind views to the ViewHolder
            viewHolder.textViewMenuData = itemView.findViewById(R.id.tvMenuData)
            viewHolder.textViewMenuDescription = itemView.findViewById(R.id.tvMenuDescription)

            // Store the ViewHolder instance as a tag on the itemView for reusability
            itemView.tag = viewHolder
        } else {
            // Retrieve the ViewHolder for reusability
            viewHolder = itemView.tag as ViewHolder
        }

        // Get the current menu item
        val menu = getItem(position)

        // Populate the TextViews with menu data
        menu?.let {
            viewHolder.textViewMenuData.text = "Data: ${it.dataM}"
            viewHolder.textViewMenuDescription.text = "Description: ${it.descriptionM}"
        }

        return itemView!!
    }

    // ViewHolder class to hold references to TextViews
    private class ViewHolder {
        lateinit var textViewMenuData: TextView
        lateinit var textViewMenuDescription: TextView
    }
}
