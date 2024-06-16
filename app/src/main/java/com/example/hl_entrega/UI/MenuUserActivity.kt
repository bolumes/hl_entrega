package com.example.hl_entrega

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

        /*
        val userId = intent.getIntExtra("USER_ID", -1) // -1 é o valor padrão se o ID não for encontrado
        if (userId != -1) {
            // Use o ID do usuário como necessário
            Toast.makeText(this, "User ID: $userId", Toast.LENGTH_SHORT).show()
        }

         */

        binding.logOut.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        binding.clickPedido.setOnClickListener {
            navigateToPedidoCliente()
        }

        //var clickCamera = binding.clickCamera
        binding.clickCamera.setOnClickListener {
            navigateToCamera()
        }

        binding.clickSeeMenu.setOnClickListener{
            navigateToSeeMenu()
        }


    }


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

    private fun logout() {
        // Add logout logic here if needed
        Toast.makeText(this, "Você saiu com sucesso", Toast.LENGTH_SHORT).show()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPedidoCliente() {
        val intent = Intent(this, CommandActivity::class.java)
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


}


