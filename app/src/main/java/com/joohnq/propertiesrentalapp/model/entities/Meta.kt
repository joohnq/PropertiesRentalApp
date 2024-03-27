package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("es_took")
    val esTook: Int? = null,
    @SerialName("es_total_hits")
    val esTotalHits: Int? = null,
    @SerialName("version")
    val version: String? = null
)