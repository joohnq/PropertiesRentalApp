package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoaDues(
    @SerialName("amount")
    val amount: Amount? = null,
    @SerialName("displayLevel")
    val displayLevel: String? = null
)