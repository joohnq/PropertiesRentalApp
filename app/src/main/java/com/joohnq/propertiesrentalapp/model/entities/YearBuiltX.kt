package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YearBuiltX(
    @SerialName("value")
    val value: Int? = null
)