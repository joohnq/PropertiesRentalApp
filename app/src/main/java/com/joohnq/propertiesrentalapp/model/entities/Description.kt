package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Description(
    @SerialName("baths")
    val baths: Int? = null,
    @SerialName("baths_full_calc")
    val bathsFullCalc: Int? = null,
    @SerialName("baths_max")
    val bathsMax: Int? = null,
    @SerialName("baths_min")
    val bathsMin: Int? = null,
    @SerialName("baths_partial_calc")
    val bathsPartialCalc: Int? = null,
    @SerialName("beds")
    val beds: Int? = null,
    @SerialName("beds_max")
    val bedsMax: Int? = null,
    @SerialName("beds_min")
    val bedsMin: Int? = null,
    @SerialName("lot_sqft")
    val lotSqft: Int? = null,
    @SerialName("sqft")
    val sqft: Int? = null,
    @SerialName("sqft_max")
    val sqftMax: Int? = null,
    @SerialName("sqft_min")
    val sqftMin: Int? = null,
    @SerialName("type")
    val type: String? = null
)