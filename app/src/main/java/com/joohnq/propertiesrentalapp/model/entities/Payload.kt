package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Payload(
    @SerialName("exactMatch")
    val exactMatch: ExactMatch? = ExactMatch(),
    @SerialName("extraResults")
    val extraResults: ExtraResults? = ExtraResults(),
    @SerialName("hasFakeResults")
    val hasFakeResults: Boolean? = false,
    @SerialName("isGeocoded")
    val isGeocoded: Boolean? = false,
    @SerialName("isRedfinServiced")
    val isRedfinServiced: Boolean? = false,
    @SerialName("responseTime")
    val responseTime: Int? = 0,
    @SerialName("sections")
    val sections: List<SectionX>? = listOf()
)