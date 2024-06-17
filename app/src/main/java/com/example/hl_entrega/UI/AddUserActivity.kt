package com.example.hl_entrega.UI

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.Models.User
import com.example.hl_entrega.R
import com.example.hl_entrega.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullnameFocusListener()
        phonenumberFocusListener()
        emailFocusListener()
        passwordFocusListener()

        //CHAMANDO A FUNÇAO NAVEGAR TO LOGIN
        binding.backProfile.setOnClickListener{
            navegateToProfile()
        }

        //INICIALISAR BD
        val db = UserDatabaseHelper(this)

        //CHAMANDO A FUNÇAO SUBMIT PARA VALIDAR OS CAMPOS
        binding.submitButton.setOnClickListener{
            submitBtn()

        }

    }


    /**
     * Function to saved user
     */
    private fun submitBtn(){

        binding.fullnameet.helperText = validFullname()
        binding.phonenumberet.helperText = validPhonenumberet()
        binding.emailet.helperText = validEmail()
        binding.password.helperText = validPassword()

        val validfullname = binding.fullnameet.helperText == null
        val validPhonenumber = binding.phonenumberet.helperText == null
        val validEmail = binding.emailet.helperText == null
        val validPassword = binding.password.helperText == null

        //Initialized db
        db = UserDatabaseHelper(this)

        if (validfullname && validPhonenumber && validEmail && validPassword){


            val fullName = binding.fullnameet.editText?.text?.toString() ?: ""
            val phoneNumber = binding.phonenumberet.editText?.text?.toString()?: ""
            val email = binding.emailet.editText?.text?.toString() ?: ""
            val password = binding.password.editText?.text?.toString() ?: ""
            val address = binding.address.editText?.text?.toString() ?: ""
            val type = binding.type.editText?.text?.toString() ?: ""
            val user = User(0, fullName, phoneNumber, email, password, address, type)

            db.insertUser(user)
            finish()
            Toast.makeText(this, "User saved sucessfully", Toast.LENGTH_SHORT).show()

            // RESET DE BUTTON BEFORE SAVIND DE INFO
            resetBtn()
        }else{
            invalidBtn()
        }

    }

    /**
     * function to reset filds
     */
    private fun resetBtn() {
        var message = "Fullneme: " + binding.fullnameet.editText
        message += "Phonenumber: " + binding.phonenumberet.editText
        message += "Email: " + binding.fullnameet.editText
        message += "Email: " + binding.emailet.editText
        message += "Password: " + binding.password.editText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("okay"){_,_ ->
                binding.fullnameet.editText?.setText(null)
                binding.phonenumberet.editText?.setText(null)
                binding.emailet.editText?.setText(null)
                binding.password.editText?.setText(null)

                binding.fullnameet.helperText = getString(R.string.required)
                binding.phonenumberet.helperText = getString(R.string.required)
                binding.emailet.helperText = getString(R.string.required)
                binding.password.helperText = getString(R.string.required)

            }


    }

    /**
     * Function to see if the filds are invalid
     */
    private fun invalidBtn() {
        var message = ""
        if (binding.fullnameet.helperText != null)
            message += "\n\nFullname: " + binding.fullnameet.helperText

        if (binding.phonenumberet.helperText != null)
            message += "\n\nPhonenumber: " + binding.phonenumberet.helperText

        if (binding.emailet.helperText != null)
            message += "\n\nEmail: " + binding.emailet.helperText

        if (binding.password.helperText != null)
            message += "\n\nPassword: " + binding.password.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("okay"){_,_ ->
                // do nothing
            }

    }

    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    private fun fullnameFocusListener(){
        binding.fullnameet.setOnFocusChangeListener{ _, focused ->
            if (!focused){
                binding.phonenumberet.helperText = validFullname()
            }
        }
    }
    /* FULLNAME FUNCTION RETURN NULL IF THE FULL NAME IS CORRECT
   * 1.ull name cannot be empty
   * 2.Full name must be at least 5 characters long
    */
    fun validFullname(): String? {
        val fullnameText = binding.fullnameet.editText?.text.toString().trim()

        // Verifica se o campo está vazio
        if (fullnameText.isEmpty()) {
            return "Full name cannot be empty"
        }

        // Verifica se o texto tem pelo menos 5 caracteres
        if (fullnameText.length < 2) {
            return "Full name must be at least 5 characters long"
        }

        // Capitaliza a primeira letra do nome
        val capitalizedFullName = fullnameText.replaceFirstChar { it.uppercase() }

        // Retorna null se o nome estiver válido
        return null
    }

    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private fun phonenumberFocusListener(){
        binding.password.setOnFocusChangeListener{ _, focused ->
            if (!focused){
                binding.phonenumberet.helperText = validPhonenumberet()
            }
        }
    }

    // PHONE NUMBER

    private fun validPhonenumberet(): String? {
        val phonenumberText = binding.phonenumberet.editText?.text.toString()

        // Remove todos os caracteres não numéricos do número de telefone
        val numericPhonenumber = phonenumberText.replace(Regex("[^\\d]"), "")

        // Verifica se o campo está vazio
        if (phonenumberText.isEmpty()) {
            return "Phone number cannot be empty"
        }

        if (numericPhonenumber.length != 9) {
            return "Phone number must have exactly 9 digits"
        }

        return null
    }


// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    // EMAIL
    private fun emailFocusListener(){
        binding.emailet.setOnFocusChangeListener{ _, focused ->
            if (!focused){
                binding.emailet.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailet.editText?.text.toString().trim()

        if (emailText.isNullOrEmpty()) {
            return "Email cannot be empty"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid email address"
        }

        return null
    }

    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    // PASSWORD
    private fun passwordFocusListener(){
        binding.password.setOnFocusChangeListener{ _, focused ->
            if (!focused){
                binding.emailet.helperText = validPassword()
            }
        }
    }


    private fun validPassword(): String? {
        val passwordText = binding.password.editText?.text.toString()

        if (passwordText.isNullOrEmpty()) {
            return "Password cannot be empty"
        }

        if (passwordText.length < 8) {
            return "Password must be at least 8 characters long"
        }

        if (!passwordText.any { it.isUpperCase() }) {
            return "Password must contain at least one uppercase character"
        }

        if (!passwordText.any { it.isDigit() }) {
            return "Password must contain at least one digit"
        }

        val specialCharacters = setOf('@', '/', '$', '&', '+', '=')
        if (!passwordText.any { it in specialCharacters }) {
            return "Password must contain at least one special character among @, /, $, &, +, ="
        }

        return null
    }


    private fun navegateToProfile(): String? {
        val navegarRegister = Intent(this, ProfileActivity::class.java)
        val resul = startActivity(navegarRegister)
        return null
    }





}
