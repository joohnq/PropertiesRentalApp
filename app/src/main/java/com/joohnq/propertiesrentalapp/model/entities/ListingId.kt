package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListingId(
    @SerialName("value")
    val value: String? = null
)