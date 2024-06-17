package com.example.hl_entrega.UI

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.R

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)


        // Referenciar o TextView
        val aboutUsTextView: TextView = findViewById(R.id.tvAboutUsContent)

        // Setar o texto do TextView com a string aboutUs
        aboutUsTextView.text = getString(R.string.aboutUs)
    }
}
