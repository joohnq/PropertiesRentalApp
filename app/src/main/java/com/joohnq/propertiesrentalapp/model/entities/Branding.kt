package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Branding(
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null
)