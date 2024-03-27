package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("address")
    val address: Address? = Address(),
    @SerialName("county")
    val county: County? = County(),
    @SerialName("neighborhoods")
    val neighborhoods: List<Neighborhood>? = listOf(),
    @SerialName("street_view_url")
    val streetViewUrl: String? = ""
)