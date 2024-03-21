package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutoCompleteRequest(
    @SerialName("errorMessage")
    val errorMessage: String? = "",
    @SerialName("payload")
    val payload: Payload? = Payload(),
    @SerialName("resultCode")
    val resultCode: Int? = 0,
    @SerialName("version")
    val version: Int? = 0
)