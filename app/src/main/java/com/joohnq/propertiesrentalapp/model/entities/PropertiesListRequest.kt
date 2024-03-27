package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertiesListRequest(
    @SerialName("data")
    val data: Data? = Data(),
    @SerialName("message")
    val message: String? = "",
    @SerialName("status")
    val status: Boolean? = false
)