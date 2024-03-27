package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.propertiesrentalapp.model.entities.AutoCompleteRequest
import com.joohnq.propertiesrentalapp.model.entities.Result
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
        MutableStateFlow<UiState<List<Result>>>(UiState.None)
    val nearYourLocationProperties get() = _nearYourLocationProperties.asStateFlow()

    private val _topRatedProperties =
        MutableStateFlow<UiState<List<Result>>>(UiState.None)
    val topRatedProperties get() = _topRatedProperties.asStateFlow()

    private val _nearYourLocationPropertiesPage = MutableStateFlow(1)
    val nearYourLocationPropertiesPage get() = _nearYourLocationPropertiesPage.asStateFlow()

    private val _topRatedPropertiesPage = MutableStateFlow(1)
    val topRatedPropertiesPagePage get() = _topRatedPropertiesPage.asStateFlow()

    private val _autoCompleteLocationTopRated =
        MutableStateFlow<UiState<AutoCompleteRequest>>(UiState.None)
    val autoCompleteLocationTopRated get() = _autoCompleteLocationTopRated.asStateFlow()

    private val _autoCompleteLocationNearYourLocation =
        MutableStateFlow<UiState<AutoCompleteRequest>>(UiState.None)
    val autoCompleteLocationNearYourLocation get() = _autoCompleteLocationTopRated.asStateFlow()

    fun getAutoComplete() {
        _autoCompleteLocationTopRated.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            locationViewModel.location.collectLatest { address ->
                address?.run {
                    val city = address.adminArea
                    val response = propertyRepository.getAutoComplete(city)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body == null) {
                                _autoCompleteLocationTopRated.value = UiState.Failure("Body is null")
                                return@withContext
                            }
                            _autoCompleteLocationTopRated.value = UiState.Success(body)
                        } else {
                            _autoCompleteLocationTopRated.value = UiState.Failure(response.message())
                            throw Exception("EROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO $response")
                        }
                    }
                } ?: run {
                    _autoCompleteLocationTopRated.value = UiState.Failure("Address is null")
                }
            }
        }
    }

    fun getAutoComplete(city: String) {
        _autoCompleteLocationNearYourLocation.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = propertyRepository.getAutoComplete(city)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body == null) {
                        _autoCompleteLocationNearYourLocation.value = UiState.Failure("Body is null")
                        return@withContext
                    }
                    _autoCompleteLocationNearYourLocation.value = UiState.Success(body)
                } else {
                    _autoCompleteLocationNearYourLocation.value = UiState.Failure(response.message())
                }
            }
        }
    }

    fun getNearYourLocationProperties(): UiState<List<Result>> {
        getAutoComplete()
        return nearYourLocationProperties.value
    }

    fun getNearYourLocationProperties(location: String) {
        _nearYourLocationProperties.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = propertyRepository.getPropertiesByLocation(location = location)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body == null) {
                        _nearYourLocationProperties.value = UiState.Failure("Body is null")
                        return@withContext
                    }
                    val newProperties = body.data?.results

                    _nearYourLocationProperties.value = UiState.Success(data = newProperties)

                    _nearYourLocationPropertiesPage.value++
                } else {
                    _nearYourLocationProperties.value = UiState.Failure("Multiple Request")
                }
            }
        }
    }
    fun getTopRatedProperties(location: String) {
        _topRatedProperties.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = propertyRepository.getPropertiesByLocation(location = location)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body == null) {
                        _topRatedProperties.value = UiState.Failure("Body is null")
                        return@withContext
                    }
                    val newProperties = body.data?.results

                    _topRatedProperties.value = UiState.Success(data = newProperties)

                    _topRatedPropertiesPage.value++
                } else {
                    _topRatedProperties.value = UiState.Failure("Multiple Request")
                }
            }
        }
    }
}