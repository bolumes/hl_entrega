package com.example.hl_entrega

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hl_entrega.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navegarBack.setOnClickListener{
            navegateMain()
        }


    }

    fun navegateMain(): String? {
        val navegarMain = Intent(this, MainActivity::class.java)
        val resul = startActivity(navegarMain)
        return null
    }
}