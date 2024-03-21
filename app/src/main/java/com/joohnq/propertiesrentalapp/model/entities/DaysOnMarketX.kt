package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DaysOnMarketX(
    @SerialName("value")
    val value: String? = null
)