package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MlsStatusId(
    @SerialName("value")
    val value: String? = null
)