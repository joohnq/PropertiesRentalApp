package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListingAddedDate(
    @SerialName("nanos")
    val nanos: Int? = null,
    @SerialName("seconds")
    val seconds: String? = null
)