package com.example.hl_entrega.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Log.d("CommandActivity", "onCreate called")
        binding = ActivityCommandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            // Inicializar o banco de dados
            db = UserDatabaseHelper(this)

            // Configurar a data atual do sistema
            binding.etData.setText(getCurrentDate())

            // Recuperar o email do Intent ou das SharedPreferences
            val email = intent.getStringExtra("user_email") ?: getEmailFromPreferences()

            if (email.isNullOrEmpty()) {
                Toast.makeText(this, "Email not found, please log in again", Toast.LENGTH_LONG).show()
                navigateToSeeMenuUser()
                return
            }

            Log.d("CommandActivity", "Email received: $email")

            // Definir o texto do EditText com o email
            binding.commander.setText(email)

            // Restaurar o estado do campo de descrição
            restoreDescription()

            // Configurar listeners
            binding.backMenu.setOnClickListener {
                saveDescription() // Salvar o estado atual do campo de descrição
                navigateToSeeMenuUser()
            }

            binding.btnSendCom.setOnClickListener {
                submitCommand(email)
            }

        } catch (e: Exception) {
            Log.e("CommandActivity", "Error in onCreate", e)
            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_LONG).show()
            navigateToSeeMenuUser()
        }
    }

    private fun navigateToSeeMenuUser() {
        val navigateSeeMenu = Intent(this, SeeMenuUser::class.java)
        startActivity(navigateSeeMenu)
    }

    private fun submitCommand(email: String) {
        val dataSystem = binding.etData.text.toString().trim()
        val description = binding.edDescrip.text.toString().trim()

        if (description.isEmpty()) {
            Toast.makeText(this, "Please fill in the description field", Toast.LENGTH_SHORT).show()
            return
        }

        val command = Command(0, dataSystem, description, email)

        // Executar a inserção no banco de dados em uma thread separada para evitar bloquear a IU
        Thread {
            try {
                db.insertCommand(command)
                runOnUiThread {
                    Toast.makeText(this, "Command sent successfully", Toast.LENGTH_SHORT).show()
                    clearFields()
                    clearSavedDescription() // Limpar o estado salvo da descrição após envio
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Failed to send command: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }

    private fun clearFields() {
        binding.edDescrip.setText("")
    }

    private fun getEmailFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", null)
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }


    /**
     * Funções para salvar e restaurar o estado do campo de descrição
     */
    private fun saveDescription() {
        val sharedPreferences = getSharedPreferences("command_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("saved_description", binding.edDescrip.text.toString())
        editor.apply()
    }

    private fun restoreDescription() {
        val sharedPreferences = getSharedPreferences("command_prefs", Context.MODE_PRIVATE)
        val savedDescription = sharedPreferences.getString("saved_description", "")
        binding.edDescrip.setText(savedDescription)
    }

    private fun clearSavedDescription() {
        val sharedPreferences = getSharedPreferences("command_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("saved_description")
        editor.apply()
    }
}
