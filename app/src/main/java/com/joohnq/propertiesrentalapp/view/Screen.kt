package com.joohnq.propertiesrentalapp.view

import com.joohnq.propertiesrentalapp.util.Constants

sealed class Screen(val rout: String) {
    object LoadingScreen : Screen(Constants.LOADING_SCREEN)
    object PresentationScreen : Screen(Constants.PRESENTATION_SCREEN)
    object LoginScreen : Screen(Constants.LOGIN_SCREEN)
    object RegisterScreen : Screen(Constants.REGISTER_SCREEN)
    object HomeScreen : Screen(Constants.HOME_SCREEN)
}