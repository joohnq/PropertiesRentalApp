package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.propertiesrentalapp.model.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationRepository: LocationRepository) :
    ViewModel() {
    private val _locationName = MutableStateFlow<UiState<String?>>(UiState.None)
    val locationName get() = _locationName.asStateFlow()

    private val _location = MutableStateFlow<UiState<String?>>(UiState.None)
    val location get() = _location.asStateFlow()

    fun getLocationName(location: Location) {
        _locationName.value = UiState.Loading
        viewModelScope.launch {
            val locationName: String? = locationRepository.getLocationName(location)
            _locationName.value = locationName?.let {
                UiState.Success(locationName)
            } ?: UiState.Failure("Location name is null")
        }
    }
}