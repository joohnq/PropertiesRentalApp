package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("agents")
    val agents: List<Agent?>? = null,
    @SerialName("disclaimer")
    val disclaimer: Disclaimer? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("listing_id")
    val listingId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null
)