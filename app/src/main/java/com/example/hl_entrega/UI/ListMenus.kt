// ListMenus.kt
package com.example.hl_entrega.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.Models.Menu
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

        // Configurando o ArrayAdapter personalizado para exibir a lista de menus no ListView
        val adapter = MenuAdapter(this, menus)
        binding.listViewMenu.adapter = adapter  // Use listViewMenu instead of listViewMenus
    }

    // Adapter personalizado para exibir a data e a descrição do menus
    class MenuAdapter(context: Context, private val menus: List<Menu>) :
        ArrayAdapter<Menu>(context, 0, menus) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val menu = getItem(position)
            val view = convertView ?: LayoutInflater.from(context).inflate(com.example.hl_entrega.R.layout.item_menu, parent, false)

            val tvDate = view.findViewById<TextView>(com.example.hl_entrega.R.id.tvMenuData)
            val tvDescription = view.findViewById<TextView>(com.example.hl_entrega.R.id.tvMenuDescription)

            // Verificando se o menu não é nulo
            menu?.let {
                tvDate.text = it.dataM
                tvDescription.text = it.descriptionM
            }

            return view
        }
    }
}
