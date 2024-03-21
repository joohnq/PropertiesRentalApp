package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DaysOnMarket(
    @SerialName("daysOnMarket")
    val daysOnMarket: DaysOnMarketX? = DaysOnMarketX(),
    @SerialName("displayLevel")
    val displayLevel: String? = "",
    @SerialName("listingAddedDate")
    val listingAddedDate: ListingAddedDate? = ListingAddedDate(),
    @SerialName("timeOnRedfin")
    val timeOnRedfin: TimeOnRedfin? = TimeOnRedfin()
)