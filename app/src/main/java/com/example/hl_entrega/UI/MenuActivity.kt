package com.example.hl_entrega

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val clickLoca = binding.clickGps
        binding.clickGps.setOnClickListener{
            navigateToGps()
        }

        val clickCom = binding.clickCom
        binding.clickCom.setOnClickListener{
            navigateToCamera()
        }

        val clickRestaurant = binding.clickRestaurant
        binding.clickRestaurant.setOnClickListener{
            navigateToAddRestau()
        }


        binding.logOut.setOnClickListener{
            showLogoutConfirmationDialog()
        }

        binding.clickProfile.setOnClickListener{
            navigateToProfile()
        }

        binding.clickMenuRestau.setOnClickListener{
            navigateToAddRestauMenu()
        }


    }

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
        // Coloque o código de logout aqui
        Toast.makeText(this, "Você saiu com sucesso", Toast.LENGTH_SHORT).show()
        navigateToMain()
    }

    private fun navigateToCommand(){
        val navigateCommand= Intent(this, CommandActivity::class.java)
        startActivity(navigateCommand)
    }

    private fun navigateToMain(){
        val navigateMain= Intent(this, MainActivity::class.java)
        startActivity(navigateMain)
    }

   /*
    private fun navigateToLocGps(){
        val navigateLocGps= Intent(this, LocalisationActivity::class.java)
        startActivity(navigateLocGps)
    }

    */

    private fun navigateToRestaurant(){
        val navigateRest= Intent(this, MenuAddActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToAddRestau(){
        val navigateRest= Intent(this, RestaurantRegisterActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToProfile(){
        val navigateRest= Intent(this, ProfileActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToAddRestauMenu(){
        val navigateRest= Intent(this, MenuAddActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToGps(){
        val navigateRest= Intent(this, GpsLocationActivity::class.java)
        startActivity(navigateRest)
    }

    private fun navigateToCamera(){
        val navigateRest= Intent(this, GetCameraActivity::class.java)
        startActivity(navigateRest)
    }





}