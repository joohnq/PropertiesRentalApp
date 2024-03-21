package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotosInfo(
    @SerialName("alternatePhotosInfo")
    val alternatePhotosInfo: List<AlternatePhotosInfo?>? = null,
    @SerialName("photoRanges")
    val photoRanges: List<PhotoRange?>? = null,
    @SerialName("posterFrameUrl")
    val posterFrameUrl: String? = null,
    @SerialName("primaryPhotoDisplayLevel")
    val primaryPhotoDisplayLevel: String? = null,
    @SerialName("scanUrl")
    val scanUrl: String? = null,
    @SerialName("secondaryPhotoDisplayLevel")
    val secondaryPhotoDisplayLevel: String? = null
)