package com.example.hl_entrega.Models

data class Admin(
    val id: Int,
    val fullname: String,
    val phonenumber: String,
    val email: String,
    val password: String,
    val address: String,
    val type: String)
