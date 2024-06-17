// ApiManager.kt
package com.example.hl_entrega.API

import com.example.hl_entrega.Models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ApiManager {

    fun getUserData(userId: Int, onResult: (Result<User>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiService.getUserData(userId)
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }

    fun createUser(user: User, onResult: (Result<User>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiService.createUser(user)
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }

    fun getAllUsers(onResult: (Result<List<User>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiService.getAllUsers()
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }

    fun updateUser(userId: Int, user: User, onResult: (Result<User>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiService.updateUser(userId, user)
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }

    fun deleteUser(userId: Int, onResult: (Result<Boolean>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiService.deleteUser(userId)
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }
}
