package com.joohnq.propertiesrentalapp.model.services

import com.joohnq.propertiesrentalapp.model.entities.Operation
import com.joohnq.propertiesrentalapp.model.entities.PropertyRentalData
import com.joohnq.propertiesrentalapp.util.Constants.RETROFIT_PATH_HOME_PROPERTIES
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyService {
    @GET(RETROFIT_PATH_HOME_PROPERTIES)
     fun getNearYourLocationProperties(
        @Query("operation") operation: Operation
    ): Flow<PropertyRentalData>
}
