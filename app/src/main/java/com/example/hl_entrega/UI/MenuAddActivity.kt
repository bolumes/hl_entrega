package com.example.hl_entrega.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.Models.Menu
import com.example.hl_entrega.databinding.ActivityMenuAddBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MenuAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuAddBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o banco de dados
        db = UserDatabaseHelper(this)

        // Configurar a data atual do sistema
        binding.dataSystem.setText(getCurrentDate())

        // Configurar listeners
        binding.backMenu.setOnClickListener {
            navigateToMenu()
        }
        binding.btnSend.setOnClickListener {
            submitBtn()
        }

        var btnListaMenu = binding.btnListaMenu
        binding.btnListaMenu.setOnClickListener {
            navigateToAddMenu()
        }

        var btnListaCommand = binding.btnListaCommand
        binding.btnListaCommand.setOnClickListener {
            navigateToLCommands()
        }


    }

    private fun navigateToMenu() {
        val navigateToMainIntent = Intent(this, MenuActivity::class.java)
        startActivity(navigateToMainIntent)
    }

    private fun navigateToAddMenu() {
        val navigateToMainIntent = Intent(this, ListMenus::class.java)
        startActivity(navigateToMainIntent)
    }

    private fun navigateToLCommands() {
        val navigateToMainIntent = Intent(this, ListCommand::class.java)
        startActivity(navigateToMainIntent)
    }


    /**
     * function to saved or to insert the menu
     */
    private fun submitBtn() {
        val dataSystem = binding.dataSystem.text.toString().trim() // Obtem a data do campo
        val description = binding.descriptionMenu.text.toString().trim()

        if (description.isEmpty()) {
            Toast.makeText(this, "Please fill in the description field", Toast.LENGTH_SHORT).show()
            return
        }

        val menu = Menu(0, dataSystem, description)
        db.insertMenu(menu)
        Toast.makeText(this, "Menu saved successfully", Toast.LENGTH_SHORT).show()
    }


    /**
     * Function to get a system data
     */
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}
