package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("city")
    val city: String? = null,
    @SerialName("coordinate")
    val coordinate: Coordinate? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("line")
    val line: String? = null,
    @SerialName("postal_code")
    val postalCode: String? = null,
    @SerialName("state")
    val state: String? = null,
    @SerialName("state_code")
    val stateCode: String? = null,
    @SerialName("street_name")
    val streetName: String? = null,
    @SerialName("street_number")
    val streetNumber: String? = null,
    @SerialName("street_suffix")
    val streetSuffix: String? = null,
    @SerialName("unit")
    val unit: String? = null
)