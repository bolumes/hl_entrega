// ApiService.kt
package com.example.hl_entrega.API

import com.example.hl_entrega.Models.User
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

object ApiService {

    private val client = ApiClient.client

    suspend fun getUserData(userId: Int): Result<User> {
        return try {
            val user: User = client.get {
                url("${ApiClient.BASE_URL}usuarios/$userId")
            }.body() // Obtenha o corpo da resposta como User
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createUser(user: User): Result<User> {
        return try {
            val createdUser: User = client.post {
                url("${ApiClient.BASE_URL}usuarios")
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body() // Obtenha o corpo da resposta como User
            Result.success(createdUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllUsers(): Result<List<User>> {
        return try {
            val users: List<User> = client.get {
                url("${ApiClient.BASE_URL}usuarios")
            }.body() // Obtenha o corpo da resposta como List<User>
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(userId: Int, user: User): Result<User> {
        return try {
            val updatedUser: User = client.put {
                url("${ApiClient.BASE_URL}usuarios/$userId")
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body() // Obtenha o corpo da resposta como User
            Result.success(updatedUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(userId: Int): Result<Boolean> {
        return try {
            val response: HttpResponse = client.delete {
                url("${ApiClient.BASE_URL}usuarios/$userId")
            }
            // Verifica se a resposta indica sucesso (status 200 ou 204 por exemplo)
            if (response.status == HttpStatusCode.OK || response.status == HttpStatusCode.NoContent) {
                Result.success(true)
            } else {
                Result.failure(Exception("Failed to delete user: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
