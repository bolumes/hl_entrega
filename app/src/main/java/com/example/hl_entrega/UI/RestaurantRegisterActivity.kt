package com.example.hl_entrega.UI

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.Models.Restaurant
import com.example.hl_entrega.databinding.ActivityRestaurantRegisterBinding

class RestaurantRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantRegisterBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o banco de dados
        db = UserDatabaseHelper(this)

        // Configurar listeners
        binding.backMenu.setOnClickListener {
            navigateToMenu()
        }
        binding.next.setOnClickListener {
            submitBtn()
        }


        binding.btnListRestau.setOnClickListener {
            navigateToListRestau()
        }
    }

    private fun navigateToMenu() {
        val navigateToMainIntent = Intent(this, MenuActivity::class.java)
        startActivity(navigateToMainIntent)
    }

    private fun navigateToListRestau() {
        val navigateToMainIntent = Intent(this, ListRestaurants::class.java)
        startActivity(navigateToMainIntent)
    }


    /**
     * Function to insert Restaurant in the database
     */
    private fun submitBtn() {
        binding.fullnameet.helperText = validFullname()
        binding.phonenumberet.helperText = validPhonenumberet()
        binding.emailet.helperText = validEmail()
        binding.address.helperText = validAddress()

        val validFullname = binding.fullnameet.helperText == null
        val validPhonenumber = binding.phonenumberet.helperText == null
        val validEmail = binding.emailet.helperText == null
        val validAddress = binding.address.helperText == null

        if (validFullname && validPhonenumber && validEmail && validAddress) {
            val fullName = binding.fullnameet.editText?.text.toString()
            val phoneNumber = binding.phonenumberet.editText?.text.toString().toInt()
            val email = binding.emailet.editText?.text.toString()
            val address = binding.address.editText?.text.toString()
            val restaurant = Restaurant(0, fullName, phoneNumber, email, address)

            db.insertRestaurant(restaurant)
            Toast.makeText(this, "Restaurant Saved successfully", Toast.LENGTH_SHORT).show()
            resetFields()
        } else {
            invalidBtn()
        }
    }

    /**
     * function to reset filds
     */
    private fun resetFields() {
        binding.fullnameet.editText?.setText(null)
        binding.phonenumberet.editText?.setText(null)
        binding.emailet.editText?.setText(null)
        binding.address.editText?.setText(null)
    }


    /**
     * function to see if the filds are invalid
     */
    private fun invalidBtn() {
        var message = ""
        if (binding.fullnameet.helperText != null) message += "Fullname: ${binding.fullnameet.helperText}\n"
        if (binding.phonenumberet.helperText != null) message += "Phonenumber: ${binding.phonenumberet.helperText}\n"
        if (binding.emailet.helperText != null) message += "Email: ${binding.emailet.helperText}\n"
        if (binding.address.helperText != null) message += "Address: ${binding.address.helperText}\n"

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    /**
     * function to know if the fullname field meets the requirements
     */
    private fun validFullname(): String? {
        val fullnameText = binding.fullnameet.editText?.text.toString().trim()
        return when {
            fullnameText.isEmpty() -> "Full name cannot be empty"
            fullnameText.length < 2 -> "Full name must be at least 2 characters long"
            else -> null
        }
    }


    /**
     * function to know if the phonenumber field meets the requirements
     */
    private fun validPhonenumberet(): String? {
        val phonenumberText = binding.phonenumberet.editText?.text.toString()
        val numericPhonenumber = phonenumberText.replace(Regex("[^\\d]"), "")
        return when {
            numericPhonenumber.isEmpty() -> "Phone number cannot be empty"
            numericPhonenumber.length != 9 -> "Phone number must have exactly 9 digits"
            else -> null
        }
    }

    /**
     * function to know if the email field meets the requirements
     */
    private fun validEmail(): String? {
        val emailText = binding.emailet.editText?.text.toString().trim()
        return when {
            emailText.isEmpty() -> "Email cannot be empty"
            !Patterns.EMAIL_ADDRESS.matcher(emailText).matches() -> "Invalid email address"
            else -> null
        }
    }


    /**
     * function to know if the address field is filled in or not
     */
    private fun validAddress(): String? {
        val addressText = binding.address.editText?.text.toString().trim()
        return when {
            addressText.isEmpty() -> "Address cannot be empty"
            else -> null
        }
    }
}
