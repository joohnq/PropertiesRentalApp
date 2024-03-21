package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Insights(
    @SerialName("displayLevel")
    val displayLevel: String? = null,
    @SerialName("hasInsight")
    val hasInsight: Boolean? = null
)