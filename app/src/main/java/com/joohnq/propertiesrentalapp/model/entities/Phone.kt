package com.joohnq.propertiesrentalapp.model.entities

data class Phone(
    val formattedPhone: String,
    val nationalNumber: Boolean,
    val phoneNumber: String,
    val phoneNumberForMobileDialing: String,
    val prefix: String
)