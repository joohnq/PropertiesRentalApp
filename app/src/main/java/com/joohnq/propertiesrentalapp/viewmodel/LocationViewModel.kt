package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import android.annotation.SuppressLint
import android.location.Address
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.joohnq.propertiesrentalapp.model.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val fusedLocationClient: FusedLocationProviderClient
) :
    ViewModel() {
    private val _locationName = MutableStateFlow<UiState<String?>>(UiState.None)
    val locationName get() = _locationName.asStateFlow()

    private val _location = MutableStateFlow<Address?>(null)
    val location get() = _location.asStateFlow()

    init {
        getLocation()
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_LOW_POWER, null).addOnSuccessListener { loc: Location? ->
            loc?.run {
                getLocationName(loc)
            } ?: run { _locationName.value = UiState.Failure("Location is null") }
        }.addOnFailureListener { error ->
            _locationName.value = UiState.Failure(error.message)
        }
    }

    fun getLocationName(location: Location) {
        _locationName.value = UiState.Loading
        viewModelScope.launch {
            val address: Address? = locationRepository.getLocationName(location)
            val locationName = address?.adminArea + ", " + address?.countryName
            _locationName.value = UiState.Success(locationName)
            _location.value = address
        }
    }
}