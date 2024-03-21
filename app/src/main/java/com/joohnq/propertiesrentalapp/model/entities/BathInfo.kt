package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BathInfo(
    @SerialName("computedFullBaths")
    val computedFullBaths: Int? = null,
    @SerialName("computedPartialBaths")
    val computedPartialBaths: Int? = null,
    @SerialName("computedTotalBaths")
    val computedTotalBaths: Double? = null,
    @SerialName("rawFullBaths")
    val rawFullBaths: Int? = null,
    @SerialName("rawHalfBaths")
    val rawHalfBaths: Int? = null,
    @SerialName("rawThreeQuarterBaths")
    val rawThreeQuarterBaths: Int? = null
)