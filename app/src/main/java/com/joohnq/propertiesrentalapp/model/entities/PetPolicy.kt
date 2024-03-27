package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetPolicy(
    @SerialName("cats")
    val cats: Boolean? = null,
    @SerialName("dogs")
    val dogs: Boolean? = null,
    @SerialName("dogs_large")
    val dogsLarge: Boolean? = null,
    @SerialName("dogs_small")
    val dogsSmall: Boolean? = null
)