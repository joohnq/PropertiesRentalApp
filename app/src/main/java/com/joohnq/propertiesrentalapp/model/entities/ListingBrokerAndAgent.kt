package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListingBrokerAndAgent(
    @SerialName("agentName")
    val agentName: String? = null,
    @SerialName("brokerName")
    val brokerName: String? = null,
    @SerialName("isRedfin")
    val isRedfin: Boolean? = null,
    @SerialName("redfinAgentId")
    val redfinAgentId: RedfinAgentId? = null
)