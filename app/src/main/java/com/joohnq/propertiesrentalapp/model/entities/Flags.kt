package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    @SerialName("is_coming_soon")
    val isComingSoon: Boolean? = null,
    @SerialName("is_contingent")
    val isContingent: Boolean? = null,
    @SerialName("is_deal_available")
    val isDealAvailable: Boolean? = null,
    @SerialName("is_foreclosure")
    val isForeclosure: Boolean? = null,
    @SerialName("is_new_construction")
    val isNewConstruction: Boolean? = null,
    @SerialName("is_new_listing")
    val isNewListing: Boolean? = null,
    @SerialName("is_pending")
    val isPending: Boolean? = null,
    @SerialName("is_plan")
    val isPlan: Boolean? = null,
    @SerialName("is_price_reduced")
    val isPriceReduced: Boolean? = null
)