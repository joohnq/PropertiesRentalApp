package com.joohnq.propertiesrentalapp.model.entities

data class ContactInfo(
    val agencyLogo: String,
    val commercialName: String,
    val contactMethod: String,
    val contactName: String,
    val micrositeShortName: String,
    val phone1: Phone,
    val totalAds: Int,
    val userType: String
)