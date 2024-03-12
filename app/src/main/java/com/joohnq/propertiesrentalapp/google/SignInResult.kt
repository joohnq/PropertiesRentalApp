package com.joohnq.propertiesrentalapp.google

import com.joohnq.propertiesrentalapp.model.entities.User

data class SignInResult(
    val data: User?,
    val errorMessage: String?
)
