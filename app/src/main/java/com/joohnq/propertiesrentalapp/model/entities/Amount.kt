package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amount(
    @SerialName("value")
    val value: String? = null
)