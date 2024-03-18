package com.joohnq.propertiesrentalapp.model.entities

data class PropertyRentalData(
    val actualPage: Int,
    val alertName: String,
    val elementList: List<PropertyItem>,
    val filter: Filter,
    val itemsPerPage: Int,
    val lowerRangePosition: Int,
    val numPaginations: Int,
    val paginable: Boolean,
    val summary: List<String>,
    val total: Int,
    val totalAppliedFilters: Int,
    val totalPages: Int,
    val upperRangePosition: Int
)