package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Brokers(
    @SerialName("listingBrokerAndAgent")
    val listingBrokerAndAgent: ListingBrokerAndAgent? = null,
    @SerialName("sellingBrokerAndAgent")
    val sellingBrokerAndAgent: SellingBrokerAndAgent? = null
)