package com.example.hl_entrega.UI

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.API.ApiService
import com.example.hl_entrega.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

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

    private fun Button.configureLanguageButton(languageCode: String) {
        setOnClickListener {
            updateLocaleAndRestart(languageCode)
        }
    }


    // XXXXXXXXXXXXXX Function to help the choise of language XXXXXXXXXXXXXX

    private fun updateLocaleAndRestart(languageCode: String) {
        saveLocale(languageCode)
        restartActivity()
    }

    private fun saveLocale(languageCode: String) {
        getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
            .putString("language_code", languageCode)
            .apply()
    }

    private fun restartActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase.updateLocale())
    }


    fun Context.updateLocale(): Context {
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val languageCode = sharedPreferences.getString("language_code", "en") ?: "en"
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        return createConfigurationContext(config)
    }

    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    /**
     * function to control the visibilities of buttons
     */
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


    // Ktor funcçao

    private fun fetchUserData(userId: Int) {
        // Usando CoroutineScope para operações de IO
        CoroutineScope(Dispatchers.IO).launch {
            // Chamada da API para obter os dados do usuário
            val result = ApiService.getUserData(userId)

            // Muda para o contexto principal para atualizar a UI
            withContext(Dispatchers.Main) {
                result.onSuccess { user ->
                    // Sucesso: atualize a UI com os dados do usuário
                    println("Dados do usuário: $user")
                    // Aqui você pode atualizar seus componentes de UI, por exemplo:
                    // textViewUserName.text = user.fullname
                    // textViewUserEmail.text = user.email
                }.onFailure { error ->
                    // Falha: trate o erro apropriadamente
                    error.printStackTrace()
                    println("Erro ao buscar dados do usuário: ${error.message}")
                    // Exemplo de como você pode exibir uma mensagem de erro para o usuário:
                    // Toast.makeText(this@MainActivity, "Erro ao carregar dados do usuário", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    //

}

