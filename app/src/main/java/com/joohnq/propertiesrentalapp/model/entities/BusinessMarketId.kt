package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessMarketId(
    @SerialName("value")
    val value: String? = null
)