package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sashe(
    @SerialName("isRedfin")
    val isRedfin: Boolean? = null,
    @SerialName("openHouseText")
    val openHouseText: String? = null,
    @SerialName("sashTypeColor")
    val sashTypeColor: String? = null,
    @SerialName("sashTypeId")
    val sashTypeId: Int? = null,
    @SerialName("sashTypeName")
    val sashTypeName: String? = null
)