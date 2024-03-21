package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Centroid(
    @SerialName("centroid")
    val centroid: CentroidX? = CentroidX(),
    @SerialName("displayLevel")
    val displayLevel: String? = ""
)