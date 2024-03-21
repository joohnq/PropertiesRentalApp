package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LotSize(
    @SerialName("amount")
    val amount: Amount? = Amount(),
    @SerialName("displayLevel")
    val displayLevel: String? = ""
)