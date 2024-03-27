package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("advertisers")
    val advertisers: List<Advertiser>? = listOf(),
    @SerialName("branding")
    val branding: List<Branding>? = listOf(),
    @SerialName("current_estimates")
    val currentEstimates: List<CurrentEstimate>? = listOf(),
    @SerialName("description")
    val description: Description? = Description(),
    @SerialName("details")
    val details: List<Detail>? = listOf(),
    @SerialName("estimate")
    val estimate: Estimate? = Estimate(),
    @SerialName("flags")
    val flags: Flags? = Flags(),
    @SerialName("has_specials")
    val hasSpecials: Boolean? = false,
    @SerialName("href")
    val href: String? = "",
    @SerialName("last_sold_date")
    val lastSoldDate: String? = "",
    @SerialName("last_sold_price")
    val lastSoldPrice: Int? = 0,
    @SerialName("lead_attributes")
    val leadAttributes: LeadAttributes? = LeadAttributes(),
    @SerialName("list_date")
    val listDate: String? = "",
    @SerialName("list_price")
    val listPrice: Int? = 0,
    @SerialName("listing_id")
    val listingId: String? = "",
    @SerialName("location")
    val location: Location? = Location(),
    @SerialName("matterport")
    val matterport: Boolean? = false,
    @SerialName("pet_policy")
    val petPolicy: PetPolicy? = PetPolicy(),
    @SerialName("photo_count")
    val photoCount: Int? = 0,
    @SerialName("photos")
    val photos: List<Photo>? = listOf(),
    @SerialName("primary_photo")
    val primaryPhoto: PrimaryPhoto? = PrimaryPhoto(),
    @SerialName("products")
    val products: Products? = Products(),
    @SerialName("property_id")
    val propertyId: String? = "",
    @SerialName("source")
    val source: Source? = Source(),
    @SerialName("status")
    val status: String? = "",
    @SerialName("__typename")
    val typename: String? = "",
)