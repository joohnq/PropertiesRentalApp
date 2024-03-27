package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutoComplete(
    @SerialName("area_type")
    val areaType: String? = null,
    @SerialName("centroid")
    val centroid: Centroid? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("city_slug_id")
    val citySlugId: String? = null,
    @SerialName("counties")
    val counties: List<County?>? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("county_needed_for_uniq")
    val countyNeededForUniq: Boolean? = null,
    @SerialName("full_address")
    val fullAddress: String? = null,
    @SerialName("geo_id")
    val geoId: String? = null,
    @SerialName("_id")
    val idUnderScore: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("_score")
    val score: Double? = null,
    @SerialName("slug_id")
    val slugId: String? = null,
    @SerialName("state")
    val state: String? = null,
    @SerialName("state_code")
    val stateCode: String? = null,
    @SerialName("university")
    val university: String? = null,
    @SerialName("university_id")
    val universityId: String? = null
)