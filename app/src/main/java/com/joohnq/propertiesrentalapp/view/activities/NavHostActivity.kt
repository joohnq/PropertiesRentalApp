package com.joohnq.propertiesrentalapp.view.activities

import UiState
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.view.Screen
import com.joohnq.propertiesrentalapp.view.activities.ui.theme.PropertiesRentalAppTheme
import com.joohnq.propertiesrentalapp.view.screens.HomeScreen
import com.joohnq.propertiesrentalapp.view.screens.LoadingScreen
import com.joohnq.propertiesrentalapp.view.screens.LoginScreen
import com.joohnq.propertiesrentalapp.view.screens.PresentationScreen
import com.joohnq.propertiesrentalapp.view.screens.RegisterScreen
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NavHostActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    private fun handleGoogleAuthResult(result: ActivityResult, changeGoogleState: () -> Unit) {
        if (result.resultCode == Activity.RESULT_OK) {
            lifecycleScope.launch {
                val signInResult =
                    googleAuthUiClient.signInWithIntent(intent = result.data ?: return@launch)
                authViewModel.authUserWithGoogle(signInResult)
            }
        } else
            changeGoogleState()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PropertiesRentalAppTheme {
//                installSplashScreen().setKeepOnScreenCondition {
//                    authViewModel.user.value is UiState.Loading
//                }
                val navController = rememberNavController()
                val user by userViewModel.user.collectAsState()
                val startDestination = Screen.LoadingScreen.rout
                val coroutineScope = rememberCoroutineScope()
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        userViewModel.getUserFromDatabase()
                    }
                }

                LaunchedEffect(user) {
                    coroutineScope.launch {
                        userViewModel.user.collect { user ->
                            navController.navigate(
                                when (user) {
                                    is UiState.Loading -> Screen.LoadingScreen.rout
                                    is UiState.Success -> Screen.HomeScreen.rout
                                    is UiState.Failure -> {
                                        Screen.PresentationScreen.rout
                                    }

                                    else -> Screen.PresentationScreen.rout
                                }
                            )
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable(Screen.PresentationScreen.rout) {
                        PresentationScreen(
                            navController = navController
                        )
                    }
                    composable(Screen.LoginScreen.rout) {
                        LoginScreen(
                            authViewModel = authViewModel,
                            googleAuthUiClient = googleAuthUiClient,
                            handleGoogleAuthResult = { result, changeGoogleState ->
                                handleGoogleAuthResult(
                                    result,
                                    changeGoogleState
                                )
                            },
                            navController = navController
                        )
                    }
                    composable(Screen.RegisterScreen.rout) {
                        RegisterScreen(
                            authViewModel = authViewModel,
                            googleAuthUiClient = googleAuthUiClient,
                            handleGoogleAuthResult = { result, changeGoogleState ->
                                handleGoogleAuthResult(
                                    result,
                                    changeGoogleState
                                )
                            },
                            navController = navController
                        )
                    }
                    composable(Screen.HomeScreen.rout) {
                        HomeScreen(
                            userViewModel = userViewModel,
                            navController = navController
                        )
                    }
                    composable(Screen.LoadingScreen.rout) {
                        LoadingScreen()
                    }
                }
            }
        }
    }
}
