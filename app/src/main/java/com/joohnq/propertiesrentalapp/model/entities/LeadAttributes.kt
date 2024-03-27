package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeadAttributes(
    @SerialName("is_premium_ldp")
    val isPremiumLdp: Boolean? = null,
    @SerialName("is_tcpa_message_enabled")
    val isTcpaMessageEnabled: Boolean? = null,
    @SerialName("opcity_lead_attributes")
    val opcityLeadAttributes: OpcityLeadAttributes? = null,
    @SerialName("show_contact_an_agent")
    val showContactAnAgent: Boolean? = null
)