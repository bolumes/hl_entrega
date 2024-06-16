package com.example.hl_entrega

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.Models.Command
import com.example.hl_entrega.databinding.ActivityCommandBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CommandActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommandBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o banco de dados
        db = UserDatabaseHelper(this)

        // Configurar a data atual do sistema
        binding.etData.setText(getCurrentDate())

        binding.backMenu.setOnClickListener{
            navigateToMenu()
        }

        binding.btnSendCom.setOnClickListener {
            submitBtn()
        }

    }

    private fun navigateToMenu(){
        val navigateMenu= Intent(this, MenuUserActivity::class.java)
        startActivity(navigateMenu)
    }

    private fun submitBtn() {
        val dataSystem = binding.etData.text.toString().trim() // Obtém a data do campo
        val description = binding.edDescrip.text.toString().trim()

        if (description.isEmpty()) {
            Toast.makeText(this, "Please fill in the description field", Toast.LENGTH_SHORT).show()
            return
        }

        val command = Command(0, dataSystem, description)
        db.insertCommand(command)
        Toast.makeText(this, "Command sent successfully", Toast.LENGTH_SHORT).show()

        // Clear the description field after successful command submission
        binding.edDescrip.setText("")
    }


    // Método para obter a data atual do sistema
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}