package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlternatePhotosInfo(
    @SerialName("groupCode")
    val groupCode: String? = null,
    @SerialName("mediaListType")
    val mediaListType: String? = null,
    @SerialName("photoType")
    val photoType: String? = null,
    @SerialName("positionSpec")
    val positionSpec: List<Int?>? = null
)