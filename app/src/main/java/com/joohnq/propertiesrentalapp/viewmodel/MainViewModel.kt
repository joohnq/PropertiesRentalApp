package com.joohnq.propertiesrentalapp.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.propertiesrentalapp.model.entities.Section
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    val rentOrBuyList = listOf("I need to rent", "I need to buy")

    private val _rentOrBuy = MutableStateFlow(Section.TO_RENT)
    val rentOrBuy get() = _rentOrBuy.asStateFlow()

    fun setRentOrBuy(value: Int) {
        val section = if(value == 0) Section.TO_RENT else Section.FOR_SALE
        _rentOrBuy.value = section
    }
}