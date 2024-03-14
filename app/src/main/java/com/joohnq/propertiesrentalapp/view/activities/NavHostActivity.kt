package com.joohnq.propertiesrentalapp.view.activities

import UiState
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.view.Screen
import com.joohnq.propertiesrentalapp.view.activities.ui.theme.PropertiesRentalAppTheme
import com.joohnq.propertiesrentalapp.view.components.PermissionDialog
import com.joohnq.propertiesrentalapp.view.screens.ChatScreen
import com.joohnq.propertiesrentalapp.view.screens.ExploreScreen
import com.joohnq.propertiesrentalapp.view.screens.HomeScreen
import com.joohnq.propertiesrentalapp.view.screens.LoadingScreen
import com.joohnq.propertiesrentalapp.view.screens.LoginScreen
import com.joohnq.propertiesrentalapp.view.screens.PresentationScreen
import com.joohnq.propertiesrentalapp.view.screens.ProfileScreen
import com.joohnq.propertiesrentalapp.view.screens.RegisterScreen
import com.joohnq.propertiesrentalapp.view.screens.SavedScreen
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.LocationViewModel
import com.joohnq.propertiesrentalapp.viewmodel.MainViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PermissionsViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

@AndroidEntryPoint
class NavHostActivity : ComponentActivity() {
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    private val mainViewModel by viewModels<MainViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val permissionsViewModel by viewModels<PermissionsViewModel>()
    private val locationViewModel by viewModels<LocationViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    private fun handleGoogleAuthResult(result: ActivityResult, changeGoogleState: () -> Unit) {
        if (result.resultCode == Activity.RESULT_OK) {
            lifecycleScope.launch {
                val signInResult =
                    googleAuthUiClient.signInWithIntent(intent = result.data ?: return@launch)
                authViewModel.authUserWithGoogle(signInResult)
            }
        } else changeGoogleState()

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PropertiesRentalAppTheme {
                val multiplePermissionResultLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions(),
                        onResult = { perms ->
                            permissionsToRequest.forEach { perm ->
                                permissionsViewModel.onPermissionResult(
                                    permission = perm, isGranted = perms[perm] == true
                                )
                            }
                        })
                var selectedItemBottomBar by remember {
                    mutableIntStateOf(0)
                }
                val onChangeBottomBar = { index: Int -> selectedItemBottomBar = index }
                val navController = rememberNavController()
                val user by userViewModel.user.collectAsState()
                val startDestination = Screen.LoadingScreen.rout
                val coroutineScope = rememberCoroutineScope()
                val dialogQueue = permissionsViewModel.visiblePermissionDialogQueue
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                val location = fusedLocationClient.lastLocation

                if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    SideEffect {
                        multiplePermissionResultLauncher.launch(
                            permissionsToRequest
                        )
                    }
                    return@PropertiesRentalAppTheme
                }

                dialogQueue.reversed().forEach { permission ->
                    PermissionDialog(
                        permission = permission,
                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                            permission
                        ),
                        onDismiss = {
                            permissionsViewModel::dismissDialog
                        },
                        onOkClick = {
                            permissionsViewModel::dismissDialog
                            multiplePermissionResultLauncher.launch(
                                arrayOf(
                                    permission
                                )
                            )
                        },
                        onGoToAppSettingsClick = ::openAppSettings
                    )

                }

                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        userViewModel.getUserFromDatabase()
                        multiplePermissionResultLauncher.launch(
                            permissionsToRequest
                        )

                        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
                            locationViewModel.getLocationName(location)
                            println("LOCATION: $location")
                        }
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
                    navController = navController, startDestination = startDestination
                ) {
                    composable(Screen.PresentationScreen.rout) {
                        PresentationScreen(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                    composable(Screen.LoginScreen.rout) {
                        LoginScreen(
                            authViewModel = authViewModel,
                            googleAuthUiClient = googleAuthUiClient,
                            handleGoogleAuthResult = { result, changeGoogleState ->
                                handleGoogleAuthResult(
                                    result, changeGoogleState
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
                                    result, changeGoogleState
                                )
                            },
                            navController = navController
                        )
                    }
                    composable(Screen.HomeScreen.rout) {
                        HomeScreen(mainViewModel = mainViewModel,
                            userViewModel = userViewModel,
                            locationViewModel = locationViewModel,
                            navController = navController,
                            selectedItemBottomBar = selectedItemBottomBar,
                            onChangeBottomBar = { onChangeBottomBar(it) })
                    }
                    composable(Screen.LoadingScreen.rout) {
                        LoadingScreen()
                    }
                    composable(Screen.ExploreScreen.rout) {
                        ExploreScreen(navController = navController,
                            selectedItemBottomBar = selectedItemBottomBar,
                            onChangeBottomBar = { onChangeBottomBar(it) })
                    }
                    composable(Screen.ChatScreen.rout) {
                        ChatScreen(navController = navController,
                            selectedItemBottomBar = selectedItemBottomBar,
                            onChangeBottomBar = { onChangeBottomBar(it) })
                    }
                    composable(Screen.SavedScreen.rout) {
                        SavedScreen(navController = navController,
                            selectedItemBottomBar = selectedItemBottomBar,
                            onChangeBottomBar = { onChangeBottomBar(it) })
                    }
                    composable(Screen.ProfileScreen.rout) {
                        ProfileScreen(navController = navController,
                            selectedItemBottomBar = selectedItemBottomBar,
                            onChangeBottomBar = { onChangeBottomBar(it) })
                    }
                }
            }
        }
    }
}
