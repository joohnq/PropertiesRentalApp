package com.joohnq.propertiesrentalapp.model.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface LocationRepository {
    suspend fun getLocationName(location: Location): String?
}

class LocationRepositoryImpl @Inject constructor(
    private val geocoder: Geocoder,
    private val context: Context
) : LocationRepository {
    override suspend fun getLocationName(location: Location): String? =
        suspendCoroutine { continuation ->
            with(location) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocation(latitude, longitude, 1) { address ->
                        if (address.isNotEmpty()) {
                            val addressLine: Address = address[0]
                            val locationName =
                                addressLine.adminArea + ", " + addressLine.countryName
                            continuation.resume(locationName)
                        } else {
                            continuation.resume("")
                        }
                    }
                }
            }
        }
}