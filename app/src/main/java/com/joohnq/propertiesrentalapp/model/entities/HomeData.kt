package com.joohnq.propertiesrentalapp.model.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    @SerialName("addressInfo")
    val addressInfo: AddressInfo? = AddressInfo(),
    @SerialName("bathInfo")
    val bathInfo: BathInfo? = BathInfo(),
    @SerialName("baths")
    val baths: Baths? = Baths(),
    @SerialName("beds")
    val beds: Beds? = Beds(),
    @SerialName("brokers")
    val brokers: Brokers? = Brokers(),
    @SerialName("businessMarketId")
    val businessMarketId: BusinessMarketId? = BusinessMarketId(),
    @SerialName("dataSourceId")
    val dataSourceId: DataSourceId? = DataSourceId(),
    @SerialName("daysOnMarket")
    val daysOnMarket: DaysOnMarket? = DaysOnMarket(),
    @SerialName("directAccessInfo")
    val directAccessInfo: DirectAccessInfo? = DirectAccessInfo(),
    @SerialName("fullBaths")
    val fullBaths: FullBaths? = FullBaths(),
    @SerialName("hoaDues")
    val hoaDues: HoaDues? = HoaDues(),
    @SerialName("hotnessData")
    val hotnessData: HotnessData? = HotnessData(),
    @SerialName("insights")
    val insights: Insights? = Insights(),
    @SerialName("lastSaleData")
    val lastSaleData: LastSaleData? = LastSaleData(),
    @SerialName("listingDisplayLevel")
    val listingDisplayLevel: String? = "",
    @SerialName("listingId")
    val listingId: ListingId? = ListingId(),
    @SerialName("listingMetadata")
    val listingMetadata: ListingMetadata? = ListingMetadata(),
    @SerialName("lotSize")
    val lotSize: LotSize? = LotSize(),
    @SerialName("marketId")
    val marketId: MarketId? = MarketId(),
    @SerialName("mlsId")
    val mlsId: String? = "",
    @SerialName("mlsStatusId")
    val mlsStatusId: MlsStatusId? = MlsStatusId(),
    @SerialName("openHouses")
    val openHouses: List<OpenHouse>? = listOf(),
    @SerialName("partialBaths")
    val partialBaths: PartialBaths? = PartialBaths(),
    @SerialName("personalization")
    val personalization: Personalization? = Personalization(),
    @SerialName("photosInfo")
    val photosInfo: PhotosInfo? = PhotosInfo(),
    @SerialName("priceInfo")
    val priceInfo: PriceInfo? = PriceInfo(),
    @SerialName("propertyId")
    val propertyId: String? = "",
    @SerialName("propertyType")
    val propertyType: String? = "",
    @SerialName("sashes")
    val sashes: List<Sashe>? = listOf(),
    @SerialName("servicePolicyId")
    val servicePolicyId: ServicePolicyId? = ServicePolicyId(),
    @SerialName("showMlsId")
    val showMlsId: ShowMlsId? = ShowMlsId(),
    @SerialName("sqftInfo")
    val sqftInfo: SqftInfo? = SqftInfo(),
    @SerialName("timezone")
    val timezone: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("yearBuilt")
    val yearBuilt: YearBuilt? = YearBuilt()
)