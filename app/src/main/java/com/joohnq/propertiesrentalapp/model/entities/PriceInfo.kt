package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceInfo(
    @SerialName("amount")
    val amount: Amount? = Amount(),
    @SerialName("displayLevel")
    val displayLevel: String? = "",
    @SerialName("homePrice")
    val homePrice: HomePrice? = HomePrice(),
    @SerialName("priceType")
    val priceType: String? = ""
)