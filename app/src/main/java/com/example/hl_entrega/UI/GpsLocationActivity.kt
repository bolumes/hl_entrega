package com.example.hl_entrega.UI

import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hl_entrega.databinding.ActivityGpsLocationBinding
import java.util.Locale

class GpsLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGpsLocationBinding
    private lateinit var geocoder: Geocoder
    private val locationPermissionCode = 2
    private var clientAddress: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGpsLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientAddressInput = binding.clientAddress
        val getLocationButton = binding.btngetlocation
        val openMapsButton = binding.openMapsButton

        // Initialize Geocoder
        geocoder = Geocoder(this, Locale.getDefault())

        getLocationButton.setOnClickListener {
            clientAddress = clientAddressInput.text.toString()
            if (clientAddress.isNullOrEmpty().not()) {
                fetchLocationFromAddress(clientAddress!!)
            } else {
                Toast.makeText(this, "Por favor, insira um endereço válido", Toast.LENGTH_SHORT).show()
            }
        }

        openMapsButton.setOnClickListener {
            val latitude = binding.latitudeInput.text.toString().toDoubleOrNull()
            val longitude = binding.longitudeInput.text.toString().toDoubleOrNull()
            if (latitude != null && longitude != null) {
                openGoogleMaps(latitude, longitude)
            } else if (!clientAddress.isNullOrEmpty()) {
                fetchLocationFromAddress(clientAddress!!)
            } else {
                Toast.makeText(this, "Por favor, insira um endereço válido", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backMenu.setOnClickListener {
            navigateToMenuActivity()
        }
    }

    /**
     * function to get location of user using the his address
     */
    private fun fetchLocationFromAddress(address: String) {
        try {
            // Geocoding to get the location coordinates
            val addressList = geocoder.getFromLocationName(address, 1)
            if (addressList.isNullOrEmpty().not()) {
                val location = addressList!![0]  // Safe to assert non-null here because of the check
                binding.latitudeInput.setText(location.latitude.toString())
                binding.longitudeInput.setText(location.longitude.toString())
                Toast.makeText(this, "Localização encontrada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Não foi possível encontrar a localização", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao buscar localização", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * function to open the google maps
     */
    private fun openGoogleMaps(latitude: Double, longitude: Double) {
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude(Portugal)"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Google Maps não está instalado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMenuActivity() {
        val navegarRegister = Intent(this, MenuActivity::class.java)
        startActivity(navegarRegister)
    }
}
