package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeZone(
    @SerialName("description")
    val description: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("olsonTimeZoneIdString")
    val olsonTimeZoneIdString: String? = null,
    @SerialName("timeZoneIdString")
    val timeZoneIdString: String? = null
)