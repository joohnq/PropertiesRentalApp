package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("autocomplete")
    val autocomplete: List<AutoComplete?>? = null,
    @SerialName("meta")
    val meta: Meta? = null,
    @SerialName("count")
    val count: Int? = null,
    @SerialName("results")
    val results: List<Result>? = null
)