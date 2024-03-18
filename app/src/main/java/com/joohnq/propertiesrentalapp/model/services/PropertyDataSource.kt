package com.joohnq.propertiesrentalapp.model.services

import com.joohnq.propertiesrentalapp.util.Constants.RETROFIT_URL_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class PropertyDataSource {
    val service: PropertyService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(RETROFIT_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PropertyService::class.java)
    }
}