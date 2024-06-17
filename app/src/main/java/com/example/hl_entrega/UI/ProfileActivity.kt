package com.example.hl_entrega.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backMenu.setOnClickListener{
            navigateToMenu()
        }

        binding.createAdmin.setOnClickListener{
            navigateToProfileAd()
        }

        binding.createUser.setOnClickListener{
            navigateToAddUser()
        }

        binding.showListUser.setOnClickListener{
            navigateToListUser()
        }

        binding.showListAdmin.setOnClickListener{
            navigateToListAdmin()
        }


    }

    private fun navigateToMenu(){
        val navigateMenu = Intent(this, MenuActivity::class.java)
        startActivity(navigateMenu)
    }

    private fun navigateToProfileAd(){
        val navigateMenu = Intent(this, AdminRegisterActivity::class.java)
        startActivity(navigateMenu)
    }


    private fun navigateToAddUser(){
        val navigateMenu = Intent(this, AddUserActivity::class.java)
        startActivity(navigateMenu)
    }




    private fun navigateToListUser(){
        val navigateMenu= Intent(this, ListUsers::class.java)
        startActivity(navigateMenu)
    }

    private fun navigateToListAdmin(){
        val navigateMenu= Intent(this, ListAdmins::class.java)
        startActivity(navigateMenu)
    }

}