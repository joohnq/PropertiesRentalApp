package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertiesListRequest(
    @SerialName("homes")
    val homes: List<Home>? = listOf()
)