package com.example.hl_entrega.UI


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityMenuUserBinding

class MenuUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera o email da Intent ou das SharedPreferences
        val email = intent.getStringExtra("email") ?: getEmailFromPreferences()

        // Se email não for nulo, salva nas SharedPreferences
        email?.let { saveEmailToPreferences(it) }

        // Vincula o TextView do layout
        val emailTextView = binding.tvUserEmail

        // Define o texto do TextView com o email
        emailTextView.text = email

        binding.logOut.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        binding.clickAbout.setOnClickListener {
            navigateToAboutUs()
        }



        binding.clickCamera.setOnClickListener {
            navigateToCamera()
        }

        binding.clickSeeMenu.setOnClickListener{
            navigateToSeeMenu()
        }
    }


    /**
     * Function to logout or to stay
     */
    private fun showLogoutConfirmationDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Do you really want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                logout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }


    /**
     * Function to confirm that you logout well
     */
    private fun logout() {
        clearEmailFromPreferences() // Limpa o email das SharedPreferences ao fazer logout
        Toast.makeText(this, "Você saiu com sucesso", Toast.LENGTH_SHORT).show()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAboutUs() {
        val intent = Intent(this, AboutUsActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToCamera() {
        val intent = Intent(this, UsergetCamera::class.java)
        startActivity(intent)
    }

    private fun navigateToSeeMenu() {
        val intent = Intent(this, SeeMenuUser::class.java)
        startActivity(intent)
    }


    /**
     * Função para salvar o email nas SharedPreferences
     */
    private fun saveEmailToPreferences(email: String) {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("email", email)
            apply()
        }
    }


    /**
     * Função para recuperar o email das SharedPreferences
     */
    private fun getEmailFromPreferences(): String? {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPref.getString("email", null)
    }

    /**
     * Função para limpar o email das SharedPreferences ao fazer logout
      */

    private fun clearEmailFromPreferences() {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("email")
            apply()
        }
    }
}
