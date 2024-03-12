package com.joohnq.propertiesrentalapp.viewmodel

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.propertiesrentalapp.google.SignInResult
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val userViewModel: UserViewModel
) :
    ViewModel() {
    private val _register = MutableStateFlow<UiState<String?>>(UiState.None)
    val register get() = _register.asStateFlow()

    private val _login = MutableStateFlow<UiState<String?>>(UiState.None)
    val login get() = _login.asStateFlow()

    private val _google = MutableStateFlow<UiState<String?>>(UiState.None)
    val google get() = _google.asStateFlow()


    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        _register.value = UiState.Loading
        viewModelScope.launch {
            val success: Boolean = firebaseRepository.createUserWithEmailAndPassword(
                email = email,
                password = password,
                onFailure = { error ->
                    _register.value = UiState.Failure(error)
                }
            )
            if (success) {
                _register.value = UiState.Success()
            } else
                _register.value = UiState.Failure("Registration failed")

            userViewModel.getUserFromDatabase()
        }
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        _login.value = UiState.Loading
        viewModelScope.launch {
            val success: Boolean = firebaseRepository.signInWithEmailAndPassword(
                email = email,
                password = password,
                onFailure = { error ->
                    _login.value = UiState.Failure(error)
                }
            )
            if (success) {
                _login.value = UiState.Success()
            } else
                _login.value = UiState.Failure("Login failed")

            userViewModel.getUserFromDatabase()
        }
    }

    fun authUserWithGoogle(signInResult: SignInResult) {
        viewModelScope.launch {
            val user = signInResult.data
            if (user != null) {
                val createdOrExist = firebaseRepository.authUserWithGoogle(user) { error ->
                    _google.value = UiState.Failure(error)
                }

                if (createdOrExist) {
                    _google.value = UiState.Success()
                    userViewModel.getUserFromDatabase()
                } else {
                    _google.value = UiState.Failure("User already exist")
                }
            } else {
                signInResult.errorMessage?.let { error ->
                    _google.value = UiState.Failure(error)
                }
            }
        }

    }

    fun setGoogle(uiState: UiState<String?>) {
        _google.value = uiState
    }

}