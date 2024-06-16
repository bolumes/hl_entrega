package com.example.hl_entrega

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hl_entrega.databinding.ActivityListRestaurantsBinding

class ListRestaurants : AppCompatActivity() {
    private lateinit var binding: ActivityListRestaurantsBinding
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var databaseHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o View Binding
        binding = ActivityListRestaurantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar o RecyclerView
        binding.recyclerViewRestaurants.layoutManager = LinearLayoutManager(this)
        restaurantAdapter = RestaurantAdapter()
        binding.recyclerViewRestaurants.adapter = restaurantAdapter

        // Inicializar o banco de dados
        databaseHelper = UserDatabaseHelper(this)

        // Carregar e mostrar a lista de restaurantes
        loadRestaurantList()
    }

    private fun loadRestaurantList() {
        val restaurantList = databaseHelper.getAllRestaurants()
        restaurantAdapter.submitList(restaurantList)
    }
}