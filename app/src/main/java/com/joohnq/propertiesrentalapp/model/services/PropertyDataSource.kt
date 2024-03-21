package com.joohnq.propertiesrentalapp.model.services

import com.joohnq.propertiesrentalapp.util.Constants.RETROFIT_URL_BASE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request: Request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "91695d416bmsh1eff7b60d287168p154ac7jsn3f1bd6926c1b")
            .addHeader("X-RapidAPI-Host", "unofficial-redfin.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}

@Singleton
class PropertyDataSource {
    val propertyService: PropertyService

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(RETROFIT_URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        propertyService = retrofit.create(PropertyService::class.java)
    }
}