package com.joohnq.propertiesrentalapp.model.repository

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface LocationRepository {
    suspend fun getLocationName(location: Location): Address?
}

class LocationRepositoryImpl @Inject constructor(
    private val geocoder: Geocoder,
) : LocationRepository {

    override suspend fun getLocationName(location: Location): Address? =
        suspendCoroutine { continuation ->
            with(location) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocation(latitude, longitude, 1) { address ->
                        if (address.isEmpty()) {
                            continuation.resume(null)
                            return@getFromLocation
                        }

                        val addressLine: Address = address[0]
                        continuation.resume(addressLine)
                    }
                } else {
                    val address = geocoder.getFromLocation(latitude, longitude, 1)

                    if (address.isNullOrEmpty()) {
                        continuation.resume(null)
                        return@suspendCoroutine
                    }

                    val addressLine: Address = address[0]
                    continuation.resume(addressLine)
                }
            }
        }

}