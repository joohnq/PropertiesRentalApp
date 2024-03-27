package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Advertiser(
    @SerialName("builder")
    val builder: String? = null,
    @SerialName("fulfillment_id")
    val fulfillmentId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("office")
    val office: Office? = null,
    @SerialName("type")
    val type: String? = null
)