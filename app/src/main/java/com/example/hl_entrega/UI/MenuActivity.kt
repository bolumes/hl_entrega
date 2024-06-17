package com.example.hl_entrega.UI


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa as SharedPreferences
        sharedPreferences = getSharedPreferences("admin_data", Context.MODE_PRIVATE)

        // Recupera o email da Intent ou das SharedPreferences
        val email = intent.getStringExtra("email") ?: getEmailFromPreferences()

        // Salva o email nas SharedPreferences se não estiver vazio
        email?.let { saveEmailToPreferences(it) }

        // Vincula o TextView do layout com o email recuperado
        binding.tvEmail.text = email

        // Configurações de clique para os botões
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.clickGps.setOnClickListener {
            navigateToGps()
        }

        binding.clickCom.setOnClickListener {
            navigateToCamera()
        }

        binding.clickRestaurant.setOnClickListener {
            navigateToAddRestau()
        }

        binding.logOut.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        binding.clickProfile.setOnClickListener {
            navigateToProfile()
        }

        binding.clickMenuRestau.setOnClickListener {
            navigateToAddRestauMenu()
        }
    }

    private fun saveEmailToPreferences(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }

    private fun getEmailFromPreferences(): String? {
        return sharedPreferences.getString("email", "")
    }


    /**
     * function to confirm to logout or stay
     */
    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do you really want to logout?")
        builder.setPositiveButton("Yes") { dialog, which ->
            // Ação ao clicar em "Sim"
            logout()
        }
        builder.setNegativeButton("No") { dialog, which ->
            // Ação ao clicar em "Não"
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun logout() {
        // Limpa o email das SharedPreferences ao fazer logout
        clearEmailFromPreferences()

        // Coloque o código de logout aqui, por exemplo:
        Toast.makeText(this, "Você saiu com sucesso", Toast.LENGTH_SHORT).show()
        navigateToMain()
    }

    private fun clearEmailFromPreferences() {
        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.apply()
    }

    private fun navigateToCommand() {
        val navigateCommand = Intent(this, CommandActivity::class.java)
        startActivity(navigateCommand)
    }

    private fun navigateToMain() {
        val navigateMain = Intent(this, MainActivity::class.java)
        startActivity(navigateMain)
        finish() // Finaliza a atividade atual após navegar para a MainActivity
    }

    private fun navigateToRestaurant() {
        val navigateRest = Intent(this, MenuAddActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToAddRestau() {
        val navigateRest = Intent(this, RestaurantRegisterActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToProfile() {
        val navigateRest = Intent(this, ProfileActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToAddRestauMenu() {
        val navigateRest = Intent(this, MenuAddActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToGps() {
        val navigateRest = Intent(this, GpsLocationActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToCamera() {
        val navigateRest = Intent(this, GetCameraActivity::class.java)
        startActivity(navigateRest)
    }
}
