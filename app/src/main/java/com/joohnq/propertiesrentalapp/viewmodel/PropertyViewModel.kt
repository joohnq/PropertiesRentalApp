package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.propertiesrentalapp.model.entities.PropertyRentalData
import com.joohnq.propertiesrentalapp.model.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val mainViewModel: MainViewModel
) : ViewModel() {
    private val _nearYourLocationProperties =
        MutableStateFlow<UiState<MutableList<PropertyRentalData>>>(UiState.None)
    val nearYourLocationProperties get() = _nearYourLocationProperties.asStateFlow()

    private val _nearYourLocationPropertiesPage = MutableStateFlow<Int>(1)
    val nearYourLocationPropertiesPage get() = _nearYourLocationPropertiesPage.asStateFlow()

    fun getNearYourLocationProperties() {
        _nearYourLocationProperties.value = UiState.Loading
        viewModelScope.launch {
            mainViewModel.rentOrBuy.collect { operation ->
                val propertyRentalData = propertyRepository.getNearYourLocationProperties(operation)
                val properties = nearYourLocationProperties.value
                when(properties){
                    is UiState.Success -> {
                        val list = properties.data
                        propertyRentalData?.let {
                            list?.add(it)
                        }
                    }
                    is UiState.Failure -> {
                    }
                    else -> Unit
                }
            }
        }
    }
}