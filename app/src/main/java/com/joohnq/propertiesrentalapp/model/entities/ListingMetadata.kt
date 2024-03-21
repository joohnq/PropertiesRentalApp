package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListingMetadata(
    @SerialName("hasVirtualTour")
    val hasVirtualTour: Boolean? = null,
    @SerialName("isNewConstruction")
    val isNewConstruction: Boolean? = null,
    @SerialName("isRedfin")
    val isRedfin: Boolean? = null,
    @SerialName("listingType")
    val listingType: String? = null,
    @SerialName("searchStatus")
    val searchStatus: String? = null
)