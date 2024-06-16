package com.example.hl_entrega

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            navigateToLogin()
        }

        binding.btnCreateAccount.setOnClickListener {
            navigateToRegister()
        }

        binding.btnGo.setOnClickListener {
            mostrarGo()
        }

    }



    private fun mostrarGo() {
        binding.btnLogin.visibility = View.VISIBLE
        binding.btnCreateAccount.visibility = View.VISIBLE
        binding.textViewcontact.visibility = View.VISIBLE
        binding.textViewEmail.visibility = View.VISIBLE
        binding.btnGo.visibility = View.INVISIBLE
    }



    private fun navigateToRegister() {
        val navigateRegister = Intent(this, UserRegisterActivity::class.java)
        startActivity(navigateRegister)
    }

    private fun navigateToLogin(){
        val navigateLogin = Intent(this, LoginActivity::class.java)
        startActivity(navigateLogin)
    }


}

