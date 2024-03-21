package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.propertiesrentalapp.model.entities.AutoCompleteRequest
import com.joohnq.propertiesrentalapp.model.entities.Home
import com.joohnq.propertiesrentalapp.model.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val locationViewModel: LocationViewModel,
    private val mainViewModel: MainViewModel
) : ViewModel() {
    private val _nearYourLocationProperties =
        MutableStateFlow<UiState<List<Home>>>(UiState.None)
    val nearYourLocationProperties get() = _nearYourLocationProperties.asStateFlow()

    private val _nearYourLocationPropertiesPage = MutableStateFlow(1)
    val nearYourLocationPropertiesPage get() = _nearYourLocationPropertiesPage.asStateFlow()

    private val _autoCompleteLocation =
        MutableStateFlow<UiState<AutoCompleteRequest>>(UiState.None)
    val autoCompleteLocation get() = _autoCompleteLocation.asStateFlow()

    fun getAutoComplete() {
        _autoCompleteLocation.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            locationViewModel.location.collectLatest { address ->
                address?.run {
                    val city = address.adminArea
                    val response = propertyRepository.getAutoComplete(city)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body == null) {
                                _autoCompleteLocation.value = UiState.Failure("Body is null")
                                return@withContext
                            }
                            _autoCompleteLocation.value = UiState.Success(body)
                        } else {
                            _autoCompleteLocation.value = UiState.Failure(response.message())
                        }
                    }
                } ?: run {
                    _autoCompleteLocation.value = UiState.Failure("Address is null")
                }
            }

        }
    }

    fun getNearYourLocationProperties(): UiState<List<Home>> {
        getAutoComplete()
        return nearYourLocationProperties.value
    }

    fun getNearYourLocationProperties(regionId: Int) {
        _nearYourLocationProperties.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = propertyRepository.getNearYourLocationProperties(regionId = regionId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body == null) {
                        _nearYourLocationProperties.value = UiState.Failure("Body is null")
                        return@withContext
                    }
                    val newProperties = body.homes

                    _nearYourLocationProperties.value = UiState.Success(data = newProperties)

                    _nearYourLocationPropertiesPage.value++
                } else {
                    _nearYourLocationProperties.value = UiState.Failure("Multiple Request")
                }
            }
        }
    }
}