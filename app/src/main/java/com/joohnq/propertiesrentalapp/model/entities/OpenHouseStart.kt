package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenHouseStart(
    @SerialName("seconds")
    val seconds: String? = null
)