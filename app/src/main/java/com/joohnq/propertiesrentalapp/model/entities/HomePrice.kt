package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomePrice(
    @SerialName("displayLevel")
    val displayLevel: String? = null,
    @SerialName("int64Value")
    val int64Value: String? = null
)