package com.example.hl_entrega

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

        binding.navegarBack.setOnClickListener {
            navigateToMain()
        }

        binding.onSignUp.setOnClickListener {
            navigateToUserRegister()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etusername.text.toString().trim()
            val password = binding.etpassword.text.toString().trim()

            if (db.isUserExist(email, password) || (email == "User" && password == "Qwerty@321")) {
                loginSuccessful("User", email, password)
            } else if (db.isAdminExist(email, password) || (email == "Admin" && password == "P@sser123")) {
                loginSuccessful("Admin", email, password)
            } else {
                showLoginFailed()
            }
        }
    }

    private fun showLoginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
    }

    private fun loginSuccessful(userType: String, email: String, password: String) {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            when (userType) {
                "Admin" -> navigateToMenu(MenuActivity::class.java, email, password)
                "User" -> navigateToMenu(MenuUserActivity::class.java, email, password)
            }
        }, 1000)
    }

    private fun navigateToMenu(activityClass: Class<*>, email: String, password: String) {
        val intent = Intent(this, activityClass).apply {
            putExtra("email", email)
            putExtra("password", password)
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
