package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Home(
    @SerialName("defaultExtension")
    val defaultExtension: DefaultExtension? = DefaultExtension(),
    @SerialName("homeData")
    val homeData: HomeData? = HomeData()
)