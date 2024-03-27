package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Estimate(
    @SerialName("estimate")
    val estimate: Int? = null
)