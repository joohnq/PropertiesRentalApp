package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenHouse(
    @SerialName("openHouseEnd")
    val openHouseEnd: OpenHouseEnd? = null,
    @SerialName("openHouseStart")
    val openHouseStart: OpenHouseStart? = null
)