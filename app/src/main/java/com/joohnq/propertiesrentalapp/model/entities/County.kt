package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class County(
    @SerialName("fips")
    val fips: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("state_code")
    val stateCode: String? = null
)