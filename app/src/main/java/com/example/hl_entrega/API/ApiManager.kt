package com.example.hl_entrega

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object ApiManager {

    private const val BASE_URL = "https://hl_entrega.pt/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    interface ApiService {

        // Exemplo de método para obter dados do usuário
        @GET("usuarios/{id}")
        fun getUserData(@Path("id") userId: Int): Call<User>

        // Métodos para operações CRUD com usuários
        @POST("usuarios")
        fun createUser(@Body user: User): Call<User>

        @GET("usuarios")
        fun getAllUsers(): Call<List<User>>

        @PUT("usuarios/{id}")
        fun updateUser(@Path("id") userId: Int, @Body user: User): Call<User>

        @DELETE("usuarios/{id}")
        fun deleteUser(@Path("id") userId: Int): Call<Void>

        // Adicione métodos semelhantes para outras entidades e operações da API
    }

    // Métodos para invocar as chamadas da API

    fun getUserData(userId: Int, callback: retrofit2.Callback<User>) {
        val call = apiService.getUserData(userId)
        call.enqueue(callback)
    }

    fun createUser(user: User, callback: retrofit2.Callback<User>) {
        val call = apiService.createUser(user)
        call.enqueue(callback)
    }

    fun getAllUsers(callback: retrofit2.Callback<List<User>>) {
        val call = apiService.getAllUsers()
        call.enqueue(callback)
    }

    fun updateUser(userId: Int, user: User, callback: retrofit2.Callback<User>) {
        val call = apiService.updateUser(userId, user)
        call.enqueue(callback)
    }

    fun deleteUser(userId: Int, callback: retrofit2.Callback<Void>) {
        val call = apiService.deleteUser(userId)
        call.enqueue(callback)
    }

    // Adicione métodos semelhantes para outras operações e entidades da API




}
