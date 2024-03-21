package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Baths(
    @SerialName("value")
    val value: Double? = null
)