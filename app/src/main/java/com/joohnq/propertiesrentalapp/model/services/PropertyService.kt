package com.joohnq.propertiesrentalapp.model.services

import com.joohnq.propertiesrentalapp.model.entities.AutoCompleteRequest
import com.joohnq.propertiesrentalapp.model.entities.PropertiesListRequest
import com.joohnq.propertiesrentalapp.util.Constants.RETROFIT_PATH_AUTO_COMPLETE
import com.joohnq.propertiesrentalapp.util.Constants.RETROFIT_PATH_PROPERTIES_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyService {
    @GET(RETROFIT_PATH_PROPERTIES_LIST)
    suspend fun getNearYourLocationProperties(
        @Query("location") location: String,
        ): Response<PropertiesListRequest>

    @GET(RETROFIT_PATH_AUTO_COMPLETE)
    suspend fun getAutoComplete(
        @Query("input") input: String
    ): Response<AutoCompleteRequest>
}
