package com.joohnq.propertiesrentalapp.model.repository

import com.joohnq.propertiesrentalapp.model.entities.Operation
import com.joohnq.propertiesrentalapp.model.entities.PropertyRentalData
import com.joohnq.propertiesrentalapp.model.services.PropertyDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface PropertyRepository {
    suspend fun getNearYourLocationProperties(operation: Operation): PropertyRentalData?
}

class PropertyRepositoryImpl @Inject constructor(
    private val scope: CoroutineScope,
    private val propertyDataSource: PropertyDataSource
) : PropertyRepository {
    override suspend fun getNearYourLocationProperties(operation: Operation): PropertyRentalData? =
        suspendCoroutine { continuation ->
            val properties = propertyDataSource.service.getNearYourLocationProperties(operation)
            scope.launch {
                properties.flowOn(Dispatchers.IO).collect {data ->
                    continuation.resume(data)
                }
            }
        }
}