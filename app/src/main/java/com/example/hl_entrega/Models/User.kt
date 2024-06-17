package com.example.hl_entrega.Models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val fullname: String,
    val phonenumber: String,
    val email: String,
    val password: String,
    val address: String,
    val type: String)

