package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Boundary(
    @SerialName("coordinates")
    val coordinates: List<List<List<List<Double?>?>?>?>? = null,
    @SerialName("type")
    val type: String? = null
)