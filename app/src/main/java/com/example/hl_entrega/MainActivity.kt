package com.example.hl_entrega

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //confirmNavigation()

        binding.btnGo.setOnClickListener{
                mostrarGo(it)
            }


        binding.btnLogin.setOnClickListener{
            navigateToLogin()
        }

        binding.btnCreateAccount.setOnClickListener{
            navegateToRegister()
        }



    }


    private fun mostrarGo(view: View) {

        val auxtBtnGo = binding.btnGo
        val auxBtnLogin = binding.btnLogin
        val auxBtnCreateAcc = binding.btnCreateAccount
        val tv_Contact = binding.textViewcontact
        val tv_Email = binding.textViewEmail

        //DEFINE THEIR VISIBILIES

        binding.btnLogin.visibility = View.VISIBLE
        binding.btnCreateAccount.visibility = View.VISIBLE
        binding.textViewcontact.visibility = View.VISIBLE
        binding.textViewEmail.visibility = View.VISIBLE
        binding.btnGo.visibility = View.INVISIBLE

    }

    /*
    private fun confirmNavigation(): String? {
           var findNav: Boolean = false
           binding.btnLogin.setOnClickListener{
               findNav = true
           }
           if (findNav == true){
               navegateToLogin()
           }

               return null
       }
    */
       fun navegateToRegister(): String? {
        val navegarRegister = Intent(this, RegisterActivity::class.java)
        val resul = startActivity(navegarRegister)
        return null
    }

    private fun navigateToLogin() {
        val navigateToLoginIntent = Intent(this, LoginActivity::class.java)
        startActivity(navigateToLoginIntent)
    }

}

