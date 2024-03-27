package com.joohnq.propertiesrentalapp.model.repository

import com.joohnq.propertiesrentalapp.model.entities.AutoCompleteRequest
import com.joohnq.propertiesrentalapp.model.entities.PropertiesListRequest
import com.joohnq.propertiesrentalapp.model.services.PropertyDataSource
import retrofit2.Response
import javax.inject.Inject

interface PropertyRepository {
    suspend fun getPropertiesByLocation(location: String): Response<PropertiesListRequest>
    suspend fun getAutoComplete(city: String): Response<AutoCompleteRequest>
}

class PropertyRepositoryImpl @Inject constructor(
    private val propertyDataSource: PropertyDataSource
) : PropertyRepository {
    private val propertyService = propertyDataSource.propertyService

    override suspend fun getPropertiesByLocation(location: String): Response<PropertiesListRequest> {
        return propertyService.getNearYourLocationProperties(location = location)
    }

    override suspend fun getAutoComplete(city: String): Response<AutoCompleteRequest> {
        return propertyService.getAutoComplete(city)
    }
}