package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoRange(
    @SerialName("endPos")
    val endPos: Int? = null,
    @SerialName("startPos")
    val startPos: Int? = null,
    @SerialName("version")
    val version: String? = null
)