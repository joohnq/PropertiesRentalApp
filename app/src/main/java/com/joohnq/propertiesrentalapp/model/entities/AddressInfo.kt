package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressInfo(
    @SerialName("centroid")
    val centroid: Centroid? = Centroid(),
    @SerialName("city")
    val city: String? = "",
    @SerialName("countryCode")
    val countryCode: String? = "",
    @SerialName("formattedStreetLine")
    val formattedStreetLine: String? = "",
    @SerialName("location")
    val location: String? = "",
    @SerialName("locationDisplayLevel")
    val locationDisplayLevel: String? = "",
    @SerialName("postalCodeDisplayLevel")
    val postalCodeDisplayLevel: String? = "",
    @SerialName("state")
    val state: String? = "",
    @SerialName("streetlineDisplayLevel")
    val streetlineDisplayLevel: String? = "",
    @SerialName("unitNumber")
    val unitNumber: String? = "",
    @SerialName("unitNumberDisplayLevel")
    val unitNumberDisplayLevel: String? = "",
    @SerialName("zip")
    val zip: String? = ""
)