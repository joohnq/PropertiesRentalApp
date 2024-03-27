package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Neighborhood(
    @SerialName("city")
    val city: String? = null,
    @SerialName("level")
    val level: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("state_code")
    val stateCode: String? = null
)