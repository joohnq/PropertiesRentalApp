package com.joohnq.propertiesrentalapp.model.entities

data class User(
    val id: String? = "",
    val name: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val authType: AuthType = AuthType.DEFAULT,
)
