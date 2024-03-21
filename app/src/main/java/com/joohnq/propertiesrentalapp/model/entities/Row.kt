package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Row(
    @SerialName("active")
    val active: Boolean? = null,
    @SerialName("businessMarketIds")
    val businessMarketIds: List<Int?>? = null,
    @SerialName("claimedHome")
    val claimedHome: Boolean? = null,
    @SerialName("countryCode")
    val countryCode: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("internalSearchVolume")
    val internalSearchVolume: Int? = null,
    @SerialName("invalidMRS")
    val invalidMRS: Boolean? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("subName")
    val subName: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null
)