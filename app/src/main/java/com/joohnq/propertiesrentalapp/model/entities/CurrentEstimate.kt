package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentEstimate(
    @SerialName("estimate")
    val estimate: Int? = null,
    @SerialName("isbest_homevalue")
    val isbestHomevalue: Boolean? = null
)