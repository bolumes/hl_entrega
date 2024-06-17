package com.example.hl_entrega.API

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {
    const val BASE_URL = "http://localhost:3000/"

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true // Ignora campos desconhecidos no JSON
                }
            )
        }

        install(Logging) {
            level = LogLevel.INFO // Configura o nível de logging
        }
    }
}
