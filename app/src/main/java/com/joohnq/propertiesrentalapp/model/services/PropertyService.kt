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
        @Query("region_id") regionId: Int,
        @Query("region_type") regionType: Int = 1,
        @Query("uipt") uipt: String = "1,2,3,4,5,6,7,8",
        @Query("status") status: Int = 9,
        ): Response<PropertiesListRequest>

    @GET(RETROFIT_PATH_AUTO_COMPLETE)
    suspend fun getAutoComplete(
        @Query("location") location: String
    ): Response<AutoCompleteRequest>
}
