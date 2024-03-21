package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastSaleData(
    @SerialName("lastSoldDate")
    val lastSoldDate: LastSoldDate? = null
)