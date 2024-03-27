package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    @SerialName("category")
    val category: String? = null,
    @SerialName("text")
    val text: List<String?>? = null
)