package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpcityLeadAttributes(
    @SerialName("flip_the_market_enabled")
    val flipTheMarketEnabled: Boolean? = null
)