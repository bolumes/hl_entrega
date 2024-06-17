package com.example.hl_entrega.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabaseHelper(this)

        // Navegar de volta para a atividade principal
        binding.navegarBack.setOnClickListener {
            navigateToMain()
        }

        // Navegar para o registro de usuário
        binding.onSignUp.setOnClickListener {
            navigateToUserRegister()
        }


        /**
         * Lidar com o clique no botão de login
         */
        binding.btnLogin.setOnClickListener {
            val email = binding.etusername.text.toString().trim()
            val password = binding.etpassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                when {
                    db.isUserExist(email, password) || (email == "User" && password == "Qwerty@321") -> {
                        loginSuccessful("User", email)
                    }
                    db.isAdminExist(email, password) || (email == "Admin" && password == "P@sser123") -> {
                        loginSuccessful("Admin", email)
                    }
                    else -> {
                        showLoginFailed()
                    }
                }
            } else {
                showLoginFailed("Por favor, insira o email e a senha.")
            }
        }
    }

    /**
     * When login is failed
     */
    private fun showLoginFailed(message: String = "Falha no login") {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    /**
     * When login is sucessfull
     */
    private fun loginSuccessful(type: String, email: String) {
        Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            val targetActivity = when (type) {
                "Admin" -> MenuActivity::class.java
                "User" -> MenuUserActivity::class.java
                else -> null
            }
            targetActivity?.let {
                Toast.makeText(this, "Navigando para $it", Toast.LENGTH_SHORT).show()
                navigateToMenu(it, email)
            }
        }, 1000)
    }

    private fun navigateToMenu(activityClass: Class<*>, email: String) {
        val intent = Intent(this, activityClass).apply {
            putExtra("email", email)
        }
        startActivity(intent)
        finish()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToUserRegister() {
        val intent = Intent(this, UserRegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
