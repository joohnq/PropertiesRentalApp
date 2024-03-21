package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotnessData(
    @SerialName("isHot")
    val isHot: Boolean? = null
)