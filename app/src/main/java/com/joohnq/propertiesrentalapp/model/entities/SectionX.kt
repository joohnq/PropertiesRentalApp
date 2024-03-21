package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionX(
    @SerialName("name")
    val name: String? = null,
    @SerialName("rows")
    val rows: List<Row?>? = null
)