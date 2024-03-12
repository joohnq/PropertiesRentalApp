package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.propertiesrentalapp.model.entities.User
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) : ViewModel() {
    private val _user = MutableStateFlow<UiState<User?>>(UiState.None)
    val user get() = _user.asStateFlow()

    fun getUserFromDatabase() {
        _user.value = UiState.Loading
        viewModelScope.launch {
            val user: User? = firebaseRepository.getUserFromDatabase(
                onFailure = { error ->
                    _user.value = UiState.Failure(error)
                }
            )

            _user.value = user?.let {
                UiState.Success(user)
            } ?: UiState.None

        }
    }

    fun logout() {
        _user.value = UiState.Loading
        viewModelScope.launch {
            val success: Boolean = firebaseRepository.logout()
            if (success)
                _user.value = UiState.None
            else
                _user.value = UiState.Failure("Logout failed")
        }
    }
}