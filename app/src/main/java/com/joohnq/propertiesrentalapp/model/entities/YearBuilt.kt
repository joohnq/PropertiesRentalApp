package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YearBuilt(
    @SerialName("displayLevel")
    val displayLevel: String? = "",
    @SerialName("yearBuilt")
    val yearBuilt: YearBuiltX? = YearBuiltX()
)