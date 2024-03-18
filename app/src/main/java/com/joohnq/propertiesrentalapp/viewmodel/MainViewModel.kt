package com.joohnq.propertiesrentalapp.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.propertiesrentalapp.model.entities.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    val rentOrBuyList = listOf("I need to rent", "I need to buy")

    private val _rentOrBuy = MutableStateFlow(Operation.rent)
    val rentOrBuy get() = _rentOrBuy.asStateFlow()

    fun setRentOrBuy(value: Int) {
        val operation = if(value == 0) Operation.rent else Operation.sale
        _rentOrBuy.value = operation
    }
}