package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenHouseEnd(
    @SerialName("seconds")
    val seconds: String? = null
)