package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DirectAccessInfo(
    @SerialName("supportPhoneNumber")
    val supportPhoneNumber: String? = null,
    @SerialName("timeZone")
    val timeZone: TimeZone? = null
)